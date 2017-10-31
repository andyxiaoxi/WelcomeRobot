package com.rongle.framework.net;


import rx.Subscriber;

/**
 * Created by tanggongwen on 17-6-20.
 */

public abstract class RxBaseSubscriber<T> extends Subscriber<BaseResponseBean<T>> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onFaild(e);
    }

    @Override
    public void onNext(BaseResponseBean<T> baseResponseBean) {

    }

    public abstract void onSuccess(T t);

    public abstract void onFaild(Throwable throwable);
}

