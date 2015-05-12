package com.yanbober.android_eventbus_demo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mShowInfo1, mShowInfo2, mShowInfo21, mShowInfo22;
    private Button mBtn1, mBtn2, mBtn21, mBtn22;

    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {
        mShowInfo1 = (TextView) findViewById(R.id.first_show);
        mBtn1 = (Button) findViewById(R.id.get_btn_1);
        mBtn1.setOnClickListener(this);

        mShowInfo2 = (TextView) findViewById(R.id.second_show);
        mBtn2 = (Button) findViewById(R.id.get_btn_2);
        mBtn2.setOnClickListener(this);

        mShowInfo21 = (TextView) findViewById(R.id.first_show_lin2);
        mBtn21 = (Button) findViewById(R.id.get_btn_1_line2);
        mBtn21.setOnClickListener(this);

        mShowInfo22 = (TextView) findViewById(R.id.second_show_line2);
        mBtn22 = (Button) findViewById(R.id.get_btn_2_line2);
        mBtn22.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册EventBus
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        //取消EventBus
        EventBus.getDefault().unregister(this);
    }

    //事件1接收者：在主线程接收
    public void onEvent(String event){
        mShowInfo1.setText(event);
    }
    //事件2接收者：在主线程接收自定义MsgBean消息
    public void onEvent(MsgBean event){
        mShowInfo21.setText(event.getMsg());
    }
    //事件3接收者：在主线程接收
    public void onEventMainThread(Integer event) {
        mShowInfo2.setText(event+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_btn_1:
                //事件1发送者：在主线程发送
                EventBus.getDefault().post("test!");
                break;
            case R.id.get_btn_1_line2:
                //事件2发送者：在主线程发送自定义MsgBean消息
                EventBus.getDefault().post(new MsgBean("type"));
                break;
            case R.id.get_btn_2:
                //事件3发送者：在子线程循环发送
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(mCount);
                        if (mCount >= 3) {
                            this.cancel();
                            mCount = 0;
                        }
                        mCount++;
                    }
                }, 0, 1000);
                break;
        }
    }
}
