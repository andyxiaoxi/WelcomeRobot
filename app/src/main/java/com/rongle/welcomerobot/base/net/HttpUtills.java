package com.rongle.welcomerobot.base.net;

import com.rongle.welcomerobot.base.net.response.BaseEntity;
import com.rongle.welcomerobot.base.net.response.LoginResponse;

import org.reactivestreams.Subscriber;

import java.security.cert.CertificateException;
import java.util.HashMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thj on 2017/11/1.
 * @author thj
 *
 * http请求工具类
 */
public class HttpUtills {

    static {
        initNetConfig();
    }
    private static Retrofit mRetrofit;
    //http接口类
    private static ApiService mApi;

    private static OkHttpClient mOkHttpClient;
    /**
     * 该方法可以配置retrofit对象
     * 目的：创建 mRetrofit，mApi
     * @return
     */
    private static void initNetConfig() {
        //配置okhttp实例对象
        mOkHttpClient = getUnsafeOkHttpClient();

        mRetrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(NetConfig.HTTP_BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        mApi = mRetrofit.create(ApiService.class);

    }



    /**
     *  登陆接口
     * @param params
     * @param callback
     */
    public static void login(HashMap<String,Object> params, final HttpCallback<BaseEntity<LoginResponse>> callback){
            HttpSecondRequest.basicHttpMethond(2,params,callback,mApi);
    }

    /**
     * 注册接口
     * @param params
     * @param callback
     */
    public static void register(HashMap<String,Object> params, final HttpCallback<LoginResponse> callback){
        HttpSecondRequest.basicHttpMethond(1,params,callback,mApi);
    }

    public static OkHttpClient getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            } };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient = okHttpClient.newBuilder()
                    .addInterceptor(InterceptorUtil.LogInterceptor())   //日志拦截器
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
