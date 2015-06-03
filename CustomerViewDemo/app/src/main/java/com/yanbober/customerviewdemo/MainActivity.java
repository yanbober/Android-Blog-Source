package com.yanbober.customerviewdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.yanbober.customerviewdemo.areachartsview.AreaChartsView;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private AreaChartsView mAreaChartsView;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAreaChartsView = (AreaChartsView) this.findViewById(R.id.area_charts_view);

        //初始化自定义图表的规格和属性
        ArrayList<Integer> mXLevel = new ArrayList<>();
        ArrayList<Integer> mYLevel = new ArrayList<>();
        ArrayList<String> mGridLevelText = new ArrayList<>();
        ArrayList<Integer> mGridColorLevel = new ArrayList<>();
        ArrayList<Integer> mGridTxtColorLevel = new ArrayList<>();
        //初始化x轴坐标区间
        mXLevel.add(0);
        mXLevel.add(60);
        mXLevel.add(90);
        mXLevel.add(100);
        mXLevel.add(110);
        mXLevel.add(120);
        //初始化y轴坐标区间
        mYLevel.add(0);
        mYLevel.add(90);
        mYLevel.add(140);
        mYLevel.add(160);
        mYLevel.add(180);
        mYLevel.add(200);
        //初始化区间颜色
        mGridColorLevel.add(Color.parseColor("#1FB0E7"));
        mGridColorLevel.add(Color.parseColor("#4FC7F4"));
        mGridColorLevel.add(Color.parseColor("#4FDDF2"));
        mGridColorLevel.add(Color.parseColor("#90E9F4"));
        mGridColorLevel.add(Color.parseColor("#B2F6F1"));
        //初始化区间文字提示颜色
        mGridTxtColorLevel.add(Color.parseColor("#EA8868"));
        mGridTxtColorLevel.add(Color.parseColor("#EA8868"));
        mGridTxtColorLevel.add(Color.parseColor("#EA8868"));
        mGridTxtColorLevel.add(Color.WHITE);
        mGridTxtColorLevel.add(Color.BLACK);
        //初始化区间文字
        mGridLevelText.add("异常");
        mGridLevelText.add("过高");
        mGridLevelText.add("偏高");
        mGridLevelText.add("正常");
        mGridLevelText.add("偏低");

        mAreaChartsView.initGridColorLevel(mGridColorLevel);
        mAreaChartsView.initGridLevelText(mGridLevelText);
        mAreaChartsView.initGridTxtColorLevel(mGridTxtColorLevel);
        mAreaChartsView.initXLevelOffset(mXLevel);
        mAreaChartsView.initYLevelOffset(mYLevel);
        mAreaChartsView.initTitleXY("投入量(H)", "产出量(H)");
    }

    @Override
    protected void onStart() {
        super.onStart();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();
                int x = random.nextInt(120) % (120 + 1) + 0;
                Random randomy = new Random();
                int y = randomy.nextInt(200) % (200 + 1) + 0;
                //随机模拟赋值
                mAreaChartsView.updateValues(x, y);
            }
        }, 0, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }
}
