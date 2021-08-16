package com.stark.mypratice.viewevent.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class ScrollerSlideView extends AppCompatButton {

    private static final String TAG = "ScrollerSlideView";

    public ScrollerSlideView(Context context) {
        this(context, null);
    }

    public ScrollerSlideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerSlideView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        Log.d(TAG + "lpq", "onTouchEvent: x = " + x + " y = " + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                int deltaY = y - lastY;
                scrollBy(-deltaX, -deltaY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }
}
