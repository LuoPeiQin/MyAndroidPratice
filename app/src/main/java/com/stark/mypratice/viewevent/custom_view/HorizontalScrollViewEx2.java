package com.stark.mypratice.viewevent.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 使用内部拦截法
 */
public class HorizontalScrollViewEx2 extends ViewGroup {

    private static final String TAG = "HorizontalScrollView";

    private int pageWidth; //每一页的宽度
    private int pageCount; //页面数量
    private int currentPageIndex; //当前页面坐标

    private VelocityTracker mVelocityTracker;
    private Scroller mScroller;

    public HorizontalScrollViewEx2(Context context) {
        this(context, null);
    }

    public HorizontalScrollViewEx2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalScrollViewEx2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mVelocityTracker = VelocityTracker.obtain();
        mScroller = new Scroller(getContext());
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    intercept = true;
                }
                break;
            default:
                intercept = true;
                break;
        }
        lastTouchX = x;
        lastTouchY = y;
        return intercept;
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = 0;
        int measureHeight = 0;
        final int childCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpaceMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpaceMode = MeasureSpec.getMode(heightMeasureSpec);

        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthSpaceMode == MeasureSpec.AT_MOST && heightSpaceMode == MeasureSpec.AT_MOST) {
            //假定所有view都是一样大小的
            final View childView = getChildAt(0);
            measureWidth = childView.getMeasuredWidth() * childCount;
            measureHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        } else if (widthMeasureSpec == MeasureSpec.AT_MOST) {
            Log.d(TAG + "lpq", "onMeasure: widthMeasureSpec == MeasureSpec.AT_MOST");
            final View childView = getChildAt(0);
            measureWidth = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(measureWidth, heightSpaceSize);
        } else if (heightMeasureSpec == MeasureSpec.AT_MOST) {
            final View childView = getChildAt(0);
            setMeasuredDimension(widthSpaceSize, childView.getMeasuredHeight());
        } else {
            final View childView = getChildAt(0);
            int measuredHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();
            setMeasuredDimension(measuredWidth * childCount, measuredHeight);
        }
    }

    /**
     * 定位
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();
        pageCount = childCount;

        for (int i = 0; i < childCount; ++i) {
            final View childView = getChildAt(i);
            if (childView != null && childView.getVisibility() != GONE) {
                final int childWidth = childView.getMeasuredWidth();
                pageWidth = childWidth;
                childView.layout(childLeft, 0, childLeft + childWidth, childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }

    private int lastTouchX;
    private int lastTouchY;

    /**
     * 触摸事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
//        Log.d(TAG + "lpq", "onTouchEvent: lastTouchX = " + lastTouchX);
        //证实：使用scrollTo和scrollBy只能改变View的内容
//        Log.d(TAG + "lpq", "onTouchEvent: left = " + getLeft());
//        Log.d(TAG + "lpq", "onTouchEvent: right = " + getRight());
        int touchRawX = (int) event.getX();
        int touchRawY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int delatX = touchRawX - lastTouchX;
                scrollBy(-delatX, 0);
                break;
            case MotionEvent.ACTION_UP:
                //根据速度和滑动距离判断是否翻页还是还原
                int srcollX = getScrollX();
                mVelocityTracker.computeCurrentVelocity(1000);
                int xVelocity = (int) mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 50) {
                    currentPageIndex = xVelocity > 0 ? currentPageIndex - 1 : currentPageIndex + 1;
                } else {
                    currentPageIndex = (srcollX + pageWidth / 2) / pageWidth;
                }
                currentPageIndex = Math.max(0, Math.min(currentPageIndex, pageCount - 1));
                int dx = srcollX - currentPageIndex * pageWidth;
                smoothScrollBy(-dx, 0);
                mVelocityTracker.clear();
                break;
        }
        lastTouchX = touchRawX;
        lastTouchY = touchRawY;
        return true;
    }

    private void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(getScrollX(), getScrollY(), dx, dy, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
