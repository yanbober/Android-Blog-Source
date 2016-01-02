package com.yanbober.viewdraghelper_demo.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.nineoldandroids.view.ViewHelper;

/**
 * 腾讯QQ侧边栏
 * 支持两种QQ侧边栏
 */
public class TencentDrawerLayout extends ViewGroup {
    private static final String TAG = "TencentDrawerLayout";

    private ViewDragHelper mHelper;

    private View mTopView;
    private View mBottomView;
    //当前实时滑动的百分比
    private float mCurMovePrecent = 0;

    public TencentDrawerLayout(Context context) {
        this(context, null);
    }

    public TencentDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TencentDrawerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Log.i(TAG, "initView------------------");
        mHelper = ViewDragHelper.create(this, 1, new ViewDragHelperCallback());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure------------------");
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
        //只有两个子成员时才有效
        if (getChildCount() == 2) {
            mBottomView = getChildAt(0);
            mTopView = getChildAt(1);

            MarginLayoutParams params = (MarginLayoutParams) mBottomView.getLayoutParams();
            //限制大小为700以下
            int width = (params.width < 0 || params.width > 700) ? 600 : params.width;
            int btmWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            measureChild(mBottomView, btmWidthMeasureSpec, heightMeasureSpec);
            measureChild(mTopView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "onLayout------------------");
        //只有layout changed了且只有两个子成员时才有效
        if (changed && getChildCount() == 2) {
            MarginLayoutParams params = (MarginLayoutParams) mBottomView.getLayoutParams();
            mBottomView.layout(params.leftMargin, params.topMargin,
                    mBottomView.getMeasuredWidth()+params.leftMargin,
                    mBottomView.getMeasuredHeight()+params.topMargin);

            params = (MarginLayoutParams) mTopView.getLayoutParams();
            mTopView.layout(params.leftMargin, params.topMargin,
                    mTopView.getMeasuredWidth()+params.leftMargin,
                    mTopView.getMeasuredHeight()+params.topMargin);
        }
    }

    //通过smoothSlideViewTo打开侧栏，因为其他两个方法只能在callback的release中调运
    public void openLeftView() {
        smoothSlideViewTo(mBottomView.getMeasuredWidth());
    }

    //通过smoothSlideViewTo关闭侧栏，因为其他两个方法只能在callback的release中调运
    public void closeLeftView() {
        smoothSlideViewTo(0);
    }

    private void smoothSlideViewTo(int finalLeft) {
        if (mHelper.getViewDragState() != ViewDragHelper.STATE_IDLE) {
            mHelper.abort();
        }
        mHelper.smoothSlideViewTo(mTopView, finalLeft, mTopView.getTop());
        invalidate();
    }

    //判断当前左边侧栏是否打开状态，通过mLeftShowSize进行判断
    public boolean isLeftIsOpened() {
        return mTopView.getLeft() == mBottomView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHelper.processTouchEvent(event);
        return true;
    }

    private class ViewDragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //上面的View是需要变化的
            return child == mTopView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //水平实时变化，最大挪动位置限制处理
            return Math.min(Math.max(0, left), mBottomView.getMeasuredWidth());
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return ((child == mTopView) ? mTopView.getMeasuredWidth() : 0);
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            //计算出mTopView变化时占总变化宽度的的实时百分比
            if (changedView == mTopView) {
                mCurMovePrecent = (mBottomView.getMeasuredWidth() - left) / (float)mBottomView.getMeasuredWidth();
            }

            //mCurMovePrecent(1,0)->topViewScale(1, 0.8)，二元一次方程m=mx+c
            float topViewScale = 0.2f * mCurMovePrecent + 0.8f;
            ViewHelper.setScaleX(mTopView, topViewScale);   //mTopView.setScaleX();
            ViewHelper.setScaleY(mTopView, topViewScale);   //mTopView.setScaleY();
            //btmPrecent(0.8, 1)->topViewScale(1, 0.8)
            float btmPrecent = 1.8f-topViewScale;
            ViewHelper.setScaleY(mBottomView, btmPrecent);   //mBottomView.setScaleX();
            ViewHelper.setScaleX(mBottomView, btmPrecent);   //mBottomView.setScaleY();
            ViewHelper.setAlpha(mBottomView, btmPrecent);    //mBottomView.setAlpha();

            float btmTransX = -mBottomView.getMeasuredWidth() * mCurMovePrecent;
            ViewHelper.setTranslationX(mBottomView, btmTransX);    //mBottomView.setTranslationX();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //当松手后让其自动滚过去
            if (releasedChild == mTopView) {
                int finalLeft = (xvel > 0 && mCurMovePrecent < 0.5) ? mBottomView.getMeasuredWidth() : 0;
                Log.i(TAG, "----------------onViewReleased finalLeft="+finalLeft);
                mHelper.settleCapturedViewAt(finalLeft, mTopView.getTop());
                invalidate();
            }
        }
    }

    @Override
    public void computeScroll() {
        if (mHelper != null && mHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
