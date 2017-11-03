package com.rongle.welcomerobot.base.net;

import android.util.Log;

import com.rongle.welcomerobot.base.net.response.BaseEntity;
import com.rongle.welcomerobot.base.net.response.LoginResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/2.
 */
public class RxBaseRequest {

    /**
     * @param obserable 该参数限定为 Observable（被观察者） 类型
     * @param observer  观察者
     * 描述：普通请求
     */
    public static <T> void  commonRequest(Observable<BaseEntity<T>> obserable,Observer<BaseEntity<T>> observer){
        commonRequest(obserable,observer,false);
    }

    /**
     * 非普通请求
     * @param obserable 被观察者
     * @param observer  观察者
     * @param isSpecial 非普通请求标识
     */
    public static <T> void commonRequest(Observable<BaseEntity<T>> obserable,Observer<BaseEntity<T>> observer,boolean isSpecial){
        if(!isSpecial){//普通请求
            obserable.subscribeOn(Schedulers.io())
                      //在没有网路的话，不要发送请求
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(observer);
        }else{
            //特殊请求

        }
    }



}
