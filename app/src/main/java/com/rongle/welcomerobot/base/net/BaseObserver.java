package com.rongle.welcomerobot.base.net;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.rongle.welcomerobot.base.net.response.BaseEntity;
import com.rongle.welcomerobot.base.net.response.LoginResponse;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/11/2.
 */
public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

    private Observable<BaseEntity<T>> mObservable;

    private Observer<BaseEntity<T>> mObserver = this;

    public BaseObserver(Observable<BaseEntity<T>> obserable) {
        mObservable = obserable;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(@NonNull BaseEntity<T> tBaseEntity) {
        onRequestEnd();
        if (tBaseEntity.isSuccess()) {
            //code=200
            try {
                onSuccees(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                //请求成功，但错误码不对
                onCodeError(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        try {
            if (e instanceof ConnectException || e instanceof TimeoutException || e instanceof
                    NetworkErrorException || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    protected void onRequestStart() {

    }

    protected void onRequestEnd() {

    }


    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(BaseEntity<T> t) throws Exception {
        if (t.getCode().isEmpty()) {
            return;
        }
        Log.e("rxjava", "code=" + t.getCode());

        if ("406".equals(t.getCode())) {          //token过期
            //重新登陆
            HttpUtills.login(new HashMap<String, Object>(), new
                    HttpCallback<BaseEntity<LoginResponse>>() {
                @Override
                public void onSuccess(BaseEntity<LoginResponse> response) {//成功
                    RxBaseRequest.commonRequest(mObservable, mObserver);
                }

                @Override
                public void onFaile() {

                }
            });
        } else { //其他错误码

        }
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BaseEntity<T> t) throws Exception;

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;
}
