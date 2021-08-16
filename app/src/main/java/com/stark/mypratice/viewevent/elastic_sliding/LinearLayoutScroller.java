package com.stark.mypratice.viewevent.elastic_sliding;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class LinearLayoutScroller extends LinearLayout{

    Scroller scroller;

    public LinearLayoutScroller(Context context) {
        this(context, null);
    }

    public LinearLayoutScroller(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearLayoutScroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
    }

    /**
     * 弹性滑动
     * @param destX
     * @param destY
     */
    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int dx = destX - scrollX;
        int dy = destY - scrollY;
        scroller.startScroll(scrollX, scrollY, dx, dy, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}
