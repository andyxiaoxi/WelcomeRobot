package com.rongle.rxjava_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rongle.rxjava_retrofit.api.NetUtil;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetUtil.login(this);
    }
}
