package com.rongle.rxjava_retrofit.api;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.rongle.rxjava_retrofit.bean.LogInfo;
import com.rongle.rxjava_retrofit.bean.MD5;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/31.
 */
public class NetUtil {

    public static void login(Context context){

        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        //1,创建retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://zmapp.rl160.com:4443/usergw/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2，创建请求对象
        NetApi service = retrofit.create(NetApi.class);

        Map<String,String> parmes = new HashMap<>();

        String md5 = MD5.getMD5("888888");
        parmes.put("phone", "13762755758");
        parmes.put("password", md5);
        parmes.put("devid", szImei);
        //3,请求服务
        service.login(parmes)            //调用请求方法
                .subscribeOn(Schedulers.newThread())   // 请求的调用在新线程
                .observeOn(Schedulers.io())            //回调在io线程
                .subscribe(new Observer<LogInfo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LogInfo logInfo) {
                        Log.e("rxjava","请求成功");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e("rxjava","请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
