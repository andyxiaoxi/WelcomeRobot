package com.rongle.measurepixel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView width_tv;
    TextView height_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        width_tv = (TextView) findViewById(R.id.width);
        height_tv = (TextView) findViewById(R.id.height);

    }

    public void click(View view){
        //获取宽高
        DisplayMetrics metrics =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        width_tv.setText("宽：    "+width);
        height_tv.setText("高：    "+height);
    }
}
