package com.rongle.retrofit;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/31.
 */
public class MyRetrofit {


    public static void request() {

        //1,创建客户端对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://zmapp.rl160.com:4443/usergw/").
                addConverterFactory(GsonConverterFactory.create()).build();
        //2,定义请求接口

        //3,调用接口
        ApiManager apiService = retrofit.create(ApiManager.class);

        Map<String,String> parmes = new HashMap<>();
        parmes.put("phone","1345");
        parmes.put("password","1345");
        parmes.put("devid","1345");
        parmes.put("platform","1345");
        parmes.put("rdelv","1345");


        Call<LoginResponse> call = apiService.getData(parmes);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    // do SomeThing
                    System.out.println("成功");
                } else {
                    //直接操作UI 返回的respone被直接解析成你指定的modle
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                System.out.println(Thread.currentThread().getName()+"失败");
                // do onFailure代码
            }
        });

    }


}
