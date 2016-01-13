package com.yanbober.scroller_demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;
/**
 * 可以左右拉伸的Layout
 */
public class HorizontalFlingLayout extends LinearLayout {
    private Scroller mScroller;

    private View mLeftView;
    private View mRightView;

    private float mInitX, mInitY;
    private float mOffsetX, mOffsetY;

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
                super.dispatchTouchEvent(ev);
                return true;
            case MotionEvent.ACTION_MOVE:
                //>0为手势向右下
                mOffsetX = ev.getX() - mInitX;
                mOffsetY = ev.getY() - mInitY;
                //横向手势跟随移动
                if (Math.abs(mOffsetX) - Math.abs(mOffsetY) > ViewConfiguration.getTouchSlop()) {
                    int offset = (int) -mOffsetX;
                    if (getScrollX() + offset > mRightView.getWidth() || getScrollX() + offset < 0) {
                        return true;
                    }
                    this.scrollBy(offset, 0);
                    mInitX = ev.getX();
                    mInitY = ev.getY();
                    return true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                //松手时刻滑动
                int offset = ((getScrollX() / (float)mRightView.getWidth()) > 0.5) ? mRightView.getWidth() : 0;
//                this.scrollTo(offset, 0);
                mScroller.startScroll(this.getScrollX(), this.getScrollY(), offset-this.getScrollX(), 0);
                invalidate();
                mInitX = 0;
                mInitY = 0;
                mOffsetX = 0;
                mOffsetY = 0;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
