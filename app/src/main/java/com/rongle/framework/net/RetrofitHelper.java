package com.rongle.framework.net;


import android.content.Context;

import com.robot.framework.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tanggongwen on 17-6-20.
 */

public class RetrofitHelper {

    private volatile static Retrofit retrofitInstance = null;
    private static final String BASE_URL = "";
    private Context context;

    /**
     * 创建Retrofit请求Api
     *
     * @param clazz Retrofit Api接口
     * @return api实例
     */
    public static <T> T createApi(Class<T> clazz) {
        return getInstance().create(clazz);
    }


    /**
     * 获取Retrofit实例
     *
     * @return Retrofit
     */
    private static Retrofit getInstance() {
        if (null == retrofitInstance) {
            synchronized (Retrofit.class) {
                if (null == retrofitInstance) { // 双重检验锁,仅第一次调用时实例化
                    retrofitInstance = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(buildOKHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofitInstance;
    }

    /**
     * 构建OkHttpClient
     *
     * @return OkHttpClient
     */
    private static OkHttpClient buildOKHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(loggingInterceptor);
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    /**
     * 获取缓存对象
     *
     * @return Cache
     */
    private static Cache getCache(String url) {
        // 获取缓存目标,SD卡
        File cacheFile = new File(url);
        // 创建缓存对象,最大缓存50m
        return new Cache(cacheFile, 1024 * 1024 * 20);
    }

    /**
     * 构建缓存拦截器
     *
     * @return Interceptor
     */
    private static Interceptor buildCacheInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                // 无网络连接时请求从缓存中读取
                if (!NetUtil.isConnected(context.getApplicationContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                // 响应内容处理
                // 在线时缓存5分钟
                // 离线时缓存4周
                Response response = chain.proceed(request);
                if (NetUtil.isConnected(context.getApplicationContext())) {
                    int maxAge = 300;
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
    }


    private OkHttpClient buildOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(loggingInterceptor);
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())                       // 在io线程中请求
                        .observeOn(AndroidSchedulers.mainThread());         // 请求完成后返回主线程处理
            }
        };
    }
}
