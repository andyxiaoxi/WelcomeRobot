package com.rongle.framework.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by TangGongWen on 2017/5/31.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != createPresenter()) {
            mPresenter = createPresenter();
        }
        setContentView(getLayoutId());
//        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract T createPresenter();

    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
//        EventBus.getDefault().unregister(this);
    }

}
