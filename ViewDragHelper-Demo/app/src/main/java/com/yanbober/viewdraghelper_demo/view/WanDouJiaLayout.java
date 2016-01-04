package com.yanbober.viewdraghelper_demo.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
/**
 * 头部可伸缩ViewGroup
 *
 * 特别注意，这里result直接返回true，混合式使用时手势自己处理一下，这里主要介绍ViewDragHelper用法而已。
 */
public class WanDouJiaLayout extends LinearLayout {
    private static final String TAG = "WanDouJiaLayout";

    private View mTopView;
    private View mBottomView;

    private ViewDragHelper mHelper;

    private int mBottomViewTop = 0;

    private float mCurX, mCurY;

    private boolean mIsFirst = true;

    public WanDouJiaLayout(Context context) {
        this(context, null);
    }

    public WanDouJiaLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WanDouJiaLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);

        mHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelperCallBack());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() != 2) {
            throw new RuntimeException("WanDouJiaLayout only need two View for child!");
        }

        mTopView = getChildAt(0);
        mBottomView = getChildAt(1);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //关于与ListView等混合时手势这里不给出了，自己处理一下即可！！！！！！！！！！！
        //特别注意，这里result直接返回true，混合式使用时手势自己处理一下，这里主要介绍ViewDragHelper用法而已。
        boolean result = true;
        return mHelper.shouldInterceptTouchEvent(ev) & result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (mIsFirst) {
            mBottomViewTop = mBottomView.getTop();
            mIsFirst = false;
        }

        int offset = mTopView.getHeight() - mBottomViewTop;
        mTopView.layout(mTopView.getLeft(), -offset,
                mTopView.getRight(), mBottomViewTop);

        ViewGroup.LayoutParams params = mBottomView.getLayoutParams();
        params.height = params.height + offset;
        mBottomView.setLayoutParams(params);

        mBottomView.layout(mBottomView.getLeft(), mBottomViewTop,
                mBottomView.getRight(), mBottomViewTop + mBottomView.getHeight());
    }

    private class ViewDragHelperCallBack extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if (child == mTopView) {
                mHelper.captureChildView(mBottomView, pointerId);
            }
            return mBottomView == child;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            //实时变化，最大挪动位置限制处理，如果想要回弹效果这个区域可以大一点
            return Math.min(Math.max(0, top), mTopView.getMeasuredHeight());
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            //灵敏度计算区域
            return getMeasuredHeight();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            int top = (yvel < 0 && mBottomViewTop < mTopView.getHeight()/2) ? 0 : mTopView.getMeasuredHeight();
            mHelper.settleCapturedViewAt(mBottomView.getLeft(), top);
            postInvalidate();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            mBottomViewTop = top;
            //重新进行绘制，因为这样就可以让上下两部分完全重新调运onLayout布局铺满屏幕
            requestLayout();
        }
    }
}
