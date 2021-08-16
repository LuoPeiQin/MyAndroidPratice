package com.stark.mypratice.viewevent.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;

public class LayoutParamsSlideView extends AppCompatTextView {
    private static final String TAG = "LayoutParamsSlide";

    public LayoutParamsSlideView(Context context) {
        this(context, null);
    }

    public LayoutParamsSlideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayoutParamsSlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int lastX = 0;
    int lastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG + "lpq", "onTouchEvent: x = " + getX());
        Log.d(TAG + "lpq", "onTouchEvent: y = " + getY());
        Log.d(TAG + "lpq", "onTouchEvent: translateX = " + getTranslationX());
        Log.d(TAG + "lpq", "onTouchEvent: translateY = " + getTranslationY());
        Log.d(TAG + "lpq", "onTouchEvent: left = " + getLeft());
        Log.d(TAG + "lpq", "onTouchEvent: right = " + getRight());
        Log.d(TAG + "lpq", "onTouchEvent: top = " + getTop());
        Log.d(TAG + "lpq", "onTouchEvent: bottom = " + getBottom());
        Log.d(TAG + "lpq", "onTouchEvent: scrollX = " + getScrollX());
        Log.d(TAG + "lpq", "onTouchEvent: scrollY = " + getScrollY());
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                int deltaY = y - lastY;
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();
                params.leftMargin = params.leftMargin + deltaX;
                params.topMargin = params.topMargin + deltaY;
                requestLayout();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }
}
