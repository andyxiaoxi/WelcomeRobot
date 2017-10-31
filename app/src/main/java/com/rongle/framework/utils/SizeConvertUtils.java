package com.rongle.framework.utils;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class SizeConvertUtils {

    /**
     * 传入B转换成MB或者GB或者KB
     *
     * @param size
     * @return String 返回可显示的大小
     */
    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = 1024 * kb;
        long gb = 1024 * mb;
        long tb = 1024 * gb;
        if (size >= tb) {
            return String.format("%.1f TB", (float) size / tb);
        } else if (size >= gb) {
            float f = (float) size / gb;
            return String.format(f > 100 ? "%.1f GB" : "%.1f GB", f);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }


}
