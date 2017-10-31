package com.rongle.rxjava_retrofit.api;

import com.rongle.rxjava_retrofit.bean.LogInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/10/31.
 */
public interface NetApi {

    //登陆接口
    @FormUrlEncoded
    @POST("login")
    Observable<LogInfo> login(@FieldMap Map<String,String> param);
}
