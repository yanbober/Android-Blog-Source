package com.yanbober.android_mvp_demo.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.yanbober.android_mvp_demo.R;
import com.yanbober.android_mvp_demo.bean.InfoBean;
import com.yanbober.android_mvp_demo.presenter.Presenter;

public class MainActivity extends ActionBarActivity implements IInfoView, View.OnClickListener{
    private EditText inputId, inputName, inputAddr;
    private Button saveBtn, loadBtn;
    private TextView infoTxt;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {
        presenter = new Presenter(this);

        inputId = (EditText) findViewById(R.id.id_input);
        inputName = (EditText) findViewById(R.id.name_input);
        inputAddr = (EditText) findViewById(R.id.addr_input);
        saveBtn = (Button) findViewById(R.id.input_confirm);
        loadBtn = (Button) findViewById(R.id.get_confirm);
        infoTxt = (TextView) findViewById(R.id.show);

        saveBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);
    }

    @Override
    public void setInfo(InfoBean info) {
        StringBuilder builder = new StringBuilder("");
        builder.append(info.getId());
        builder.append("\n");
        builder.append(info.getName());
        builder.append("\n");
        builder.append(info.getAddress());

        infoTxt.setText(builder.toString());
    }

    @Override
    public InfoBean getInfo() {
        InfoBean info = new InfoBean();
        info.setId(Integer.parseInt(inputId.getText().toString()));
        info.setName(inputName.getText().toString());
        info.setAddress(inputAddr.getText().toString());
        return info;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_confirm:
                presenter.saveInfo(getInfo());
                break;
            case R.id.get_confirm:
                presenter.getInfo();
                break;
        }
    }
}
