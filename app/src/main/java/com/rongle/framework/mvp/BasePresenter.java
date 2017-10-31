package com.rongle.framework.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by TangGongWen on 2017/5/31.
 */

public class BasePresenter<V extends IBaseView> {
    protected Reference<V> mViewRef;

    protected void attch(V view) {
        this.mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    protected boolean isViewAttached() {
        return mViewRef != null & mViewRef.get() != null;
    }

    protected void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
