package com.yanbober.viewdraghelper_demo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WanDouJiaActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    private Button mTopBtn;
    private ViewPager mViewPager;
    private PagerTabStrip mPagerTabStrip;

    private ThisViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_wandoujia_layout);

        init();
    }

    private void init() {
        mTopBtn = (Button) findViewById(R.id.top_click_btn);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip_can_exchange);

        mTopBtn.setOnClickListener(this);

        mPagerTabStrip.setDrawFullUnderline(false);
        mPagerTabStrip.setTabIndicatorColor(Color.GREEN);
//        mPagerTabStrip.setTextSpacing(100);

        mAdapter = new ThisViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == mTopBtn) {
            Toast.makeText(getApplicationContext(), "上面测试按钮按下！", Toast.LENGTH_LONG).show();
        }
    }
}
