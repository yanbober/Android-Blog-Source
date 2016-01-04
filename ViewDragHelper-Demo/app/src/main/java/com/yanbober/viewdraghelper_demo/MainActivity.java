package com.yanbober.viewdraghelper_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mVerticalDrawerLayoutBtn;
    private Button mViewDrawerHealperBtn;
    private Button mViewDrawerHealperBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mVerticalDrawerLayoutBtn = (Button) findViewById(R.id.btn_1);
        mViewDrawerHealperBtn = (Button) findViewById(R.id.btn_2);
        mViewDrawerHealperBtn1 = (Button) findViewById(R.id.btn_3);
        mVerticalDrawerLayoutBtn.setOnClickListener(this);
        mViewDrawerHealperBtn.setOnClickListener(this);
        mViewDrawerHealperBtn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mVerticalDrawerLayoutBtn) {
            startActivity(new Intent(MainActivity.this, VerticalDrawerLayoutActivity.class));
        }
        else if (v == mViewDrawerHealperBtn) {
            startActivity(new Intent(MainActivity.this, ViewDragHelperActivity.class));
        }
        else if (v == mViewDrawerHealperBtn1) {
            startActivity(new Intent(MainActivity.this, WanDouJiaActivity.class));
        }
    }
}
