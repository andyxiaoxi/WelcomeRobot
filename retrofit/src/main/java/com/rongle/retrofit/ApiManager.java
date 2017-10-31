package com.rongle.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/10/31.
 */
public interface ApiManager {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> getData(@FieldMap Map<String,String> map);
}
