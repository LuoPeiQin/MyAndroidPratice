package com.stark.mypratice.view.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.customview.widget.ViewDragHelper

class DragUpDownLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    val viewDragHelper = ViewDragHelper.create(this, DragHelperCallback())

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return viewDragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        viewDragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            postInvalidateOnAnimation()
        }
    }


    inner class DragHelperCallback : ViewDragHelper.Callback() {
        var startTop = 0

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
//            startTop = capturedChild.top
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            val childHeight = releasedChild.height
            val center = (releasedChild.top + releasedChild.bottom) / 2
            if (center > height / 2) {
                viewDragHelper.settleCapturedViewAt(0, height - childHeight)
            } else {
                viewDragHelper.settleCapturedViewAt(0, 0)
            }
            postInvalidateOnAnimation()
        }
    }
}