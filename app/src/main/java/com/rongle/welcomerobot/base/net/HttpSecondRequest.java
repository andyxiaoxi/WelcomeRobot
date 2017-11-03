package com.rongle.welcomerobot.base.net;

import android.util.Log;

import com.rongle.welcomerobot.base.net.response.BaseEntity;
import com.rongle.welcomerobot.base.net.response.LoginResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by Administrator on 2017/11/2.
 * http请求的第二阶层
 */
public class HttpSecondRequest {
    private static ApiService mApi;
    private static HashMap<String,Object> mParams;

    /**
     * 核心方法，准备请求参数，发送请求
     * @param flag
     * @param params
     * @param callback
     * @param apiService
     */
    public static void basicHttpMethond(int flag, HashMap<String,Object> params, HttpCallback callback, ApiService apiService){
        mApi = apiService;
        mParams = params;
        switch (flag){
            case 1: createRegister(callback);
                break;
            case 2: createLogin(callback);
                break;
        }

    }

    private static void createLogin(final HttpCallback callback) {
        //1,构建被观察者对象
        Observable<BaseEntity<LoginResponse>> observable=mApi.login(mParams); //创建一个被观察者
        //rxjava ,请求网络
        //2,构建观察者对象
        Observer<BaseEntity<LoginResponse>> observer = new BaseObserver<LoginResponse>(observable) {
            @Override
            protected void onSuccees(BaseEntity<LoginResponse> t) throws Exception {
                if(callback!=null){
                    callback.onSuccess(t);
                }
            }
            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                if(isNetWorkError){
                    Log.e("rxjava","网络错误,请检查你的网络状态");
                }

                if(callback!=null){

                }
            }
        };
        //3,发起请求
        RxBaseRequest.commonRequest(observable,observer);
    }


    private static void createRegister(final HttpCallback callback) {
        Observable<BaseEntity<Object>> observable=mApi.register(mParams);
        Observer<BaseEntity<Object>> observer = new BaseObserver<Object>(observable){
            @Override
            protected void onSuccees(BaseEntity<Object> t) throws Exception {
                if(callback!=null){
                    callback.onSuccess(t.getData());
                }
            }
            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                if(callback!=null){

                }
            }
        };
        RxBaseRequest.commonRequest(observable,observer);
    }
}
