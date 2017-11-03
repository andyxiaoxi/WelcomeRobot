package com.rongle.welcomerobot.base.net;

import com.rongle.welcomerobot.base.net.response.BaseEntity;
import com.rongle.welcomerobot.base.net.response.LoginResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by thj on 2017/11/1.
 * @author thj
 * 网络接口类
 */
public interface ApiService {

    /**
     * 登录接口
     * @return
     */
    @FormUrlEncoded
    @POST("/usergw/login")
    Observable<BaseEntity<LoginResponse>> login(@FieldMap HashMap<String,Object> parmas);


    @FormUrlEncoded
    @POST("/usergw/register")
    Observable<BaseEntity<Object>> register(@FieldMap HashMap<String,Object> parmas);
}
