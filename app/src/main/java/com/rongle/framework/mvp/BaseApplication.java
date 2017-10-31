package com.rongle.framework.mvp;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.robot.framework.utils.L;
import com.robot.onekeyservice.R;
import com.robot.onekeyservice.base.net.UrlManager;
import com.robot.onekeyservice.video.sdkvideo.RLRtcEventHandler;

import io.agora.rtc.RtcEngine;

/**
 * Created by tony on 17-7-17.
 */

public class BaseApplication extends Application {


    private static final String TAG = "BaseApplication";
    private static Handler mainHandler;

    private static Context Gcontext;

    private static RtcEngine rtcEngine;  //核心类

    private static RLRtcEventHandler mRlRtcEventHandler;                //rtc事件的回调

    public String appid = "";    //官网申请的id


    @Override
    public void onCreate() {
        super.onCreate();
        mainHandler = new Handler();
        Gcontext = getApplicationContext();
        initRemoteUrl();
        L.init();
        //设置appid
        appid = Gcontext.getString(R.string.app_id);
        mRlRtcEventHandler = new RLRtcEventHandler();
        rtcEngine = RtcEngine.create(Gcontext, appid, mRlRtcEventHandler);
    }

    /**
     * 初始化BaseUrl
     * */
    private void initRemoteUrl() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            final String versionName = pi.versionName;
            Log.i(TAG, "initRemoteUrl: "+versionName);
            if (!TextUtils.isEmpty(versionName)) {
                if (versionName.contains("测试")) {
                    UrlManager.setEnvironment(UrlManager.Environment.Debug);
                    L.isDebug = true;
                }else {
                    UrlManager.setEnvironment(UrlManager.Environment.Release);
                    L.isDebug = false;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取全局context对象
     *
     * @return
     */
    public static Context getGloableContext() {
        if (Gcontext == null) {
            throw new RuntimeException("Gcontext is null..");
        }
        return Gcontext;
    }


    /**
     * 获取rtc对象
     *
     * @return
     */
    public static RtcEngine getRtcEngine() {
        if (rtcEngine == null) {
            throw new RuntimeException("rtcEngine is null");
        }
        return rtcEngine;
    }

    /**
     * 获取rtc回掉对象
     *
     * @return
     */
    public static RLRtcEventHandler getRLRtcEventHandler() {
        if (mRlRtcEventHandler == null) {
            throw new RuntimeException("Gcontext is null..");
        }
        return mRlRtcEventHandler;
    }

    /**
     * 获取handler
     *
     * @return
     */
    public static Handler getMainHandler() {
        return mainHandler;
    }

}
