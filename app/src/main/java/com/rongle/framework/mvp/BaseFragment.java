package com.rongle.framework.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by TangGongWen on 2017/5/31.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        View rootView = inflater.inflate(getLayoutId(), container, false);
        initView(rootView);
        initData();
        return rootView;
    }

    protected abstract T createPresenter();

    protected abstract void initView(View rootView);

    protected abstract void initData();

    protected abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }
}
