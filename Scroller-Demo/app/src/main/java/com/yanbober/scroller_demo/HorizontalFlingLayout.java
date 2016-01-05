package com.yanbober.scroller_demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class HorizontalFlingLayout extends LinearLayout {
    private Scroller mScroller;

    private View mLeftView;
    private View mRightView;

    private float mInitX, mInitY;
    private int mOffsetX = 0;

    public HorizontalFlingLayout(Context context) {
        this(context, null);
    }

    public HorizontalFlingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalFlingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setOrientation(LinearLayout.HORIZONTAL);

        mScroller = new Scroller(getContext(), null, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() != 2) {
            throw new RuntimeException("Only need two child view! Please check you xml file!");
        }

        mLeftView = getChildAt(0);
        mRightView = getChildAt(1);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mInitX = ev.getX();
                mInitY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //>0为手势向右下
                float xOffset = ev.getX() - mInitX;
                float yOffset = ev.getY() - mInitY;
                //横向手势
                if (Math.abs(xOffset) - Math.abs(yOffset) > ViewConfiguration.getTouchSlop()) {

                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mInitX = 0;
                mInitY = 0;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


}
