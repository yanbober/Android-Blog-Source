package com.yanbober.viewdraghelper_demo.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.yanbober.viewdraghelper_demo.R;

/**
 * 腾讯QQ左侧栏TencentLeftLayout
 */
public class TencentLeftLayout extends LinearLayout {
    public TencentLeftLayout(Context context) {
        this(context, null);
    }

    public TencentLeftLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TencentLeftLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setBackgroundColor(Color.TRANSPARENT);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_tencent_left, this, false);
        addView(view);
    }
}
