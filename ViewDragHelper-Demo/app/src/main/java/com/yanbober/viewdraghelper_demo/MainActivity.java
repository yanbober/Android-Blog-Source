package com.yanbober.viewdraghelper_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mVerticalDrawerLayoutBtn;
    private Button mViewDrawerHealperBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mVerticalDrawerLayoutBtn = (Button) findViewById(R.id.btn_1);
        mViewDrawerHealperBtn = (Button) findViewById(R.id.btn_2);
        mVerticalDrawerLayoutBtn.setOnClickListener(this);
        mViewDrawerHealperBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mVerticalDrawerLayoutBtn) {
            startActivity(new Intent(MainActivity.this, VerticalDrawerLayoutActivity.class));
        }
        else if (v == mViewDrawerHealperBtn) {
            startActivity(new Intent(MainActivity.this, ViewDragHelperActivity.class));
        }
    }
}
