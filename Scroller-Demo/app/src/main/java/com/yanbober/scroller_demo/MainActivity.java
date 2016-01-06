package com.yanbober.scroller_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton1 = (Button) findViewById(R.id.btn1);
        mButton1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButton1) {
            startActivity(new Intent(MainActivity.this, HorScrollerLayoutActivity.class));
        }
    }
}
