package com.rongle.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.Map;

/**
 * SharedPreferences封装类
 * Created by 陈自强 on 2016/11/15.
 */
public class SharedPreferencesUtils {
    public static final String TAG = "SharedPreferencesUtils";
    private static SharedPreferencesUtils instance;
    private static SharedPreferences sp ;
    private static SharedPreferences.Editor editor ;

    public static SharedPreferencesUtils getInstance() {
        if (instance == null) {
            synchronized (SharedPreferencesUtils.class) {
                if (instance == null)
                    instance = new SharedPreferencesUtils();
            }
        }
        return instance;
    }

    /**
     * 初始化
     * @param context
     * @param fileName
     */
    public void initSP(Context context, String fileName){
        if (context == null) {
            return;
        }
        String FILE_NAME ;
        if (TextUtils.isEmpty(fileName)) {
            FILE_NAME = "share_data";
        }else {
            FILE_NAME = fileName;
        }
        sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     * @param key
     * @param object
     */
    public  void put(String key, Object object) {
        Log.i(TAG, "put: Object+"+object);
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        }
        apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * @param key
     * @param defaultObject
     * @return
     */
    public Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     * @param key
     */
    public  void removeValue(String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        apply(editor);
    }

    /**
     * 清除所有数据
     *
     */
    public  void clearAll() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public  boolean containsKey( String key) {
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

//    public  final Method sApplyMethod = findApplyMethod();
//    /**
//     * 反射查找apply的方法
//     *
//     * @return
//     */
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    private static Method findApplyMethod() {
//        try {
//            Class clz = SharedPreferences.Editor.class;
//            return clz.getMethod("apply");
//        } catch (NoSuchMethodException e) {
//        }
//
//        return null;
//    }

    /**
     * 如果找到则使用apply执行，否则使用commit
     *
     * @param editor
     */
    private  void apply(SharedPreferences.Editor editor) {
//        try {
//            if (sApplyMethod != null) {
//                sApplyMethod.invoke(editor);
//                return;
//            }
//        } catch (IllegalArgumentException e) {
//        } catch (IllegalAccessException e) {
//        } catch (InvocationTargetException e) {
//        }
        editor.commit();
    }
}
