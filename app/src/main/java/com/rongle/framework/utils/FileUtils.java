package com.rongle.framework.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();

    /**
     * 删除AppCache
     *
     * @param context
     */
    public static void deletAppCache(Context context) {
        String fileName = "/data/data/" + context.getPackageName();
        Log.i(TAG, "doRecovery: " + fileName);
        File xmlFile = new File(fileName);
        delete(xmlFile);
    }

    /**
     * 删除整个文件或者文件夹
     *
     * @param file
     */
    public static void delete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            file.delete();
        }
    }

    public static File getCacheDir(String dirName) {
        File result;
        result = new File(Environment.getExternalStorageDirectory()+"/data/com.robot.login/cache/",
                dirName);
        if (result.exists() || result.mkdirs()) {
            return result;
        } else {
            return null;
        }
    }

    /**
     * 检查磁盘空间是否大于10mb
     *
     * @return true 大于
     */
    public static boolean isDiskAvailable() {
        long size = getDiskAvailableSize();
        return size > 10 * 1024 * 1024; // > 10bm
    }

    /**
     * 获取磁盘可用空间
     *
     * @return byte 单位 kb
     */
    public static long getDiskAvailableSize() {
        if (!existsSdcard()) return 0;
        File path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
        StatFs stat = new StatFs(path.getAbsolutePath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
        // (availableBlocks * blockSize)/1024 KIB 单位
        // (availableBlocks * blockSize)/1024 /1024 MIB单位
    }

    public static Boolean existsSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static long getFileOrDirSize(File file) {
        if (!file.exists()) return 0;
        if (!file.isDirectory()) return file.length();

        long length = 0;
        File[] list = file.listFiles();
        if (list != null) { // 文件夹被删除时, 子文件正在被写入, 文件属性异常返回null.
            for (File item : list) {
                length += getFileOrDirSize(item);
            }
        }

        return length;
    }

    public static void writeTokenFile(String token) {
        File file = FileUtils.getCacheDir("token");
        try {
            if (file != null) {
                file.delete();
                file.createNewFile();
            }else {
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(token.getBytes());
            outputStream.flush();
            outputStream.close();
            L.i("写入成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            L.i("写入失败");
        } catch (IOException e) {
            e.printStackTrace();
            L.i("写入失败");
        }
    }

    public static String readTokenFile() {
        File file = FileUtils.getCacheDir("token");
        if (file!=null) {
            try {
                FileInputStream fin = new FileInputStream(file);
                //把字节流转化为字符流
                BufferedReader buffer = new BufferedReader(new InputStreamReader(fin));
                //读取文件中的用户名和密码
                String text = buffer.readLine();
                return text;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }
}
