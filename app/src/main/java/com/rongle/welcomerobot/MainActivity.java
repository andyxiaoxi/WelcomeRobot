package com.rongle.welcomerobot;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rongle.framework.mvp.BaseActivity;
import com.rongle.framework.mvp.BasePresenter;
import com.rongle.framework.utils.MD5;
import com.rongle.welcomerobot.base.net.HttpCallback;
import com.rongle.welcomerobot.base.net.HttpUtills;
import com.rongle.welcomerobot.base.net.response.BaseEntity;
import com.rongle.welcomerobot.base.net.response.LoginResponse;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.request)
    Button request;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick(R.id.request)
    public void onViewClicked() {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);

        String szImei = TelephonyMgr.getDeviceId();
        HashMap<String, Object> parmas = new HashMap<>();
        String md5 = MD5.getMD5("888888");
        String newmd5 = MD5.getMD5(md5);
        parmas.put("phone", "13762755758");
        parmas.put("password", newmd5);
        parmas.put("devid", szImei);
        /*parmas.put("platform","888888");
        parmas.put("rdelv","888888");*/
        HttpUtills.login(parmas, new HttpCallback<BaseEntity<LoginResponse>>() {
            @Override
            public void onSuccess(BaseEntity<LoginResponse> response) {
                Log.e("rxjava1", response.getMsg());
            }

            @Override
            public void onFaile() {

            }
        });
    }
}