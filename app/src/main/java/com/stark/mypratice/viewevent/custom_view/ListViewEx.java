package com.stark.mypratice.viewevent.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class ListViewEx extends ListView {

    private HorizontalScrollViewEx2 horizontalScrollViewEx2;

    public ListViewEx(Context context) {
        this(context, null);
    }

    public ListViewEx(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int lastX;
    private int lastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                horizontalScrollViewEx2.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int delatX = x - lastX;
                int delatY = y - lastY;
                if (Math.abs(delatX) > Math.abs(delatY)) {
                    horizontalScrollViewEx2.requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = x;
        lastY = y;
        return super.dispatchTouchEvent(ev);
    }

    public HorizontalScrollViewEx2 getHorizontalScrollViewEx2() {
        return horizontalScrollViewEx2;
    }

    public void setHorizontalScrollViewEx2(HorizontalScrollViewEx2 horizontalScrollViewEx2) {
        this.horizontalScrollViewEx2 = horizontalScrollViewEx2;
    }
}
