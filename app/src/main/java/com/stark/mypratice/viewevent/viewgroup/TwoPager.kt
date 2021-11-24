/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: TwoPager
 * Author: lpq
 * Date: 2021/11/23 20:36
 * Description: 用一句话描述下
 */
package com.stark.mypratice.viewevent.viewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.OverScroller
import androidx.core.view.children
import kotlin.math.abs

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.viewevent.viewgroup
 * @ClassName: TwoPager
 * @Description:
 * 1、包含两个页面
 * 2、可以左右滑动翻页；
 * 3、滑动距离不够时自动缩回；
 * 4、快划时view滑动；
 * 5、有滑动动画；
 * @Author: lpq
 * @CreateDate: 2021/11/23 20:36
 */
class TwoPager(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    /**
     * 1、监听点击事件；
     * 2、监听滑动距离和速度；
     * 3、UP做滑动判断，开始滑动或缩回动画；
     */
    private var downX = 0f
    private var downY = 0f
    private var scrolling = false
    private var downScrollX = 0f
    private val overScroll = OverScroller(context)
    private val velocityTracker = VelocityTracker.obtain()
    private val viewConfiguration = ViewConfiguration.get(context)
    private val minFlingVelocity = viewConfiguration.scaledMinimumFlingVelocity
    private val maxFlingVelocity = viewConfiguration.scaledMaximumFlingVelocity
    private val minTouchSlop = viewConfiguration.scaledPagingTouchSlop

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(ev)
        var result = false
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                scrolling = false
                downX = ev.x
                downY = ev.y
                downScrollX = scrollX.toFloat()
            }
            MotionEvent.ACTION_MOVE -> {
                if (!scrolling) {
                    val scrollX = downX - ev.x
                    if (abs(scrollX) > minTouchSlop) {
                        scrolling = true
                        parent.requestDisallowInterceptTouchEvent(true)
                        result = true
                    }
                }
            }
        }
        return result
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(ev)
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                scrolling = false
                downX = ev.x
                downY = ev.y
                downScrollX = scrollX.toFloat()
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = (downX - ev.x + downScrollX).toInt().coerceAtLeast(0).coerceAtMost(width)
                scrollTo(dx, 0)
            }
            MotionEvent.ACTION_UP -> {
                velocityTracker.computeCurrentVelocity(1000, maxFlingVelocity.toFloat())
                val xVelocity = velocityTracker.xVelocity
                val scrollX = scrollX
                val targetPage = if (abs(xVelocity) < minFlingVelocity) {
                    if (scrollX > width / 2) 1 else 0
                } else {
                    if (xVelocity < 0) 1 else 0
                }
                val scrollDistance = if (targetPage == 1) width - scrollX else -scrollX
                overScroll.startScroll(getScrollX(), 0, scrollDistance, 0)
                postInvalidateOnAnimation()
            }
        }
        return true
    }

    override fun computeScroll() {
        if (overScroll.computeScrollOffset()) {
            scrollTo(overScroll.currX, overScroll.currY)
            postInvalidateOnAnimation()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = 0
        var right = width
        var top = 0
        var bottom = height
        for (child in children) {
            child.layout(left, 0, right, bottom)
            left += width
            right += width
        }
    }
}