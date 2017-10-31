package com.rongle.framework.utils;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;


/**
 * Log统一管理类（选择使用Log，或者Looger）
 */
public class L {

    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static final String TAG = "ONE-KEY-CS";

    /**
     * 初始化Logger工具
     */
    public static void init(){
        if (isDebug){
            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                    .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
//                    .methodCount(5)         // (Optional) How many method line to show. Default 2
//                    .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                    .tag(TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER

                    .build();

            Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        }


    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i( TAG,msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d( TAG,msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e( TAG,msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v( TAG,msg);
    }




    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Logger.i(tag , msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Logger.d(tag ,  msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag , msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }



    // 下面使用Logger传递-打印栈信息
    public static void ir(String tag, String msg) {
        if (isDebug)
            Logger.i(tag+","+msg);
    }

    public static void dr(String tag, String msg) {
        if (isDebug)
            Logger.d(tag+","+ msg);
    }

    public static void er(String tag, String msg) {
        if (isDebug)
            Logger.e(tag+","+ msg);
    }

    public static void vr(String tag, String msg) {
        if (isDebug)
            Logger.v(tag+","+ msg);
    }

    public static void xml(String msg) {
        if (isDebug)
            Logger.xml(msg);
    }

    public static void json(String msg) {
        if (isDebug)
            Logger.json(msg);
    }
}
