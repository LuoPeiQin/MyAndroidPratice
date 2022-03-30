/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: EcgView
 * Author: lpq
 * Date: 2022/3/28 13:54
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.business

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.widget.OverScroller
import com.stark.mypratice.dp
import kotlin.math.max

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.business
 * @ClassName: EcgView
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/3/28 13:54
 */
class EcgView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var drawWidth = 0f
    var drawHeight = 0f

    // 网格宽度
    val POINT_INTERVAL = 0.5f.dp * 4
    val GRID_WIDTH = 4.5f.dp * 2
    val GRID_LINE_WIDTH = 0.5f.dp * 2
    val LINE_WIDTH = 1f.dp

    // 底部粗线
    private val mThickLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 底部细线
    private val mLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 心电线
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val ecgPath = Path()

    // 数据
    val MAX_PARAMS = 8000
    private var ecgData = mutableListOf<Int>()
    var ecgStartX = 0f
    private val mOverScroller: OverScroller = OverScroller(context)
    private var mVelocityTracker = VelocityTracker.obtain()

    /**
     * 动态模式
     * 设置是否可以触控 true 用于静态展示(默认值) false 用于动态展示
     */
    var touchEnable = true

    init {
        mThickLinePaint.color = Color.parseColor("#FFCCCC")
        mThickLinePaint.strokeWidth = GRID_LINE_WIDTH
        mLinePaint.color = Color.parseColor("#FFE6E6")
        mLinePaint.strokeWidth = GRID_LINE_WIDTH
        mPaint.color = Color.parseColor("#575E66")
        mPaint.strokeWidth = LINE_WIDTH
        mPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        drawWidth = max(POINT_INTERVAL * ecgData.size, width.toFloat())
        drawHeight = height.toFloat()
        val scale = drawHeight * 0.8f / MAX_PARAMS / 2
        val centerY = drawHeight / 2
        var tempY = 0f
        ecgStartX = 0f
        ecgPath.moveTo(ecgStartX, centerY)
        for ((index, item) in ecgData.withIndex()) {
            if (ecgStartX <= drawWidth) {
                tempY = item * scale + centerY
                ecgStartX += POINT_INTERVAL
                ecgPath.lineTo(ecgStartX, tempY)
            } else {
                Log.i("lpq", "drawEcgLine: 已画点数 = $index")
                break
            }
        }
        Log.i("lpq", "onDraw: drawHeight = $drawHeight drawWidth = $drawWidth")
    }

    /**
     * 静态心电图
     */
    fun setData(data: MutableList<Int>) {
        if (!touchEnable) {
            // 可能还在添加数据
            return
        }
        ecgData.clear()
        ecgData.addAll(data)
        drawWidth = max(POINT_INTERVAL * ecgData.size, width.toFloat())
        val scale = drawHeight * 0.8f / MAX_PARAMS / 2
        val centerY = drawHeight / 2
        var tempY = 0f
        ecgPath.reset()
        ecgPath.moveTo(ecgStartX, centerY)
        for ((index, item) in ecgData.withIndex()) {
            if (ecgStartX <= drawWidth) {
                tempY = item * scale + centerY
                ecgStartX += POINT_INTERVAL
                ecgPath.lineTo(ecgStartX, tempY)
            } else {
                Log.i("lpq", "drawEcgLine: 已画点数 = $index")
                break
            }
        }
        invalidate()
    }

    /**
     * 动态心电图 -- 持续添加数据
     */
    fun addData(data: MutableList<Int>) {
        ecgData.addAll(data)
        drawWidth = max(POINT_INTERVAL * ecgData.size, width.toFloat())
        val scale = drawHeight * 0.8f / MAX_PARAMS / 2
        val centerY = drawHeight / 2
        var tempY = 0f
        for ((index, item) in data.withIndex()) {
            if (ecgStartX <= drawWidth) {
                tempY = item * scale + centerY
                ecgStartX += POINT_INTERVAL
                ecgPath.lineTo(ecgStartX, tempY)
            } else {
                Log.i("lpq", "drawEcgLine: 已画点数 = $index")
                break
            }
        }
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        // 1、画底部网格线
        // 2、画心电线
        drawBgLine(canvas)
        drawEcgLine(canvas)
        if (!touchEnable) {
            scrollTo((drawWidth - width).toInt(), 0)
        }
    }

    /**
     * 画ECG线
     */
    private fun drawEcgLine(canvas: Canvas) {
        canvas.drawPath(ecgPath, mPaint)
    }

    /**
     * 画背景线
     */
    private fun drawBgLine(canvas: Canvas) {
        var curY = 0f + GRID_LINE_WIDTH
        // 画横线
        var i = 0
        while (curY <= drawHeight) {
            if (i % 5 == 0) {
                canvas.drawLine(0f, curY, drawWidth, curY, mThickLinePaint)
            } else {
                canvas.drawLine(0f, curY, drawWidth, curY, mLinePaint)
            }
            i++
            curY += GRID_WIDTH
        }
        // 画纵线
        var curX = 0f + GRID_LINE_WIDTH
        var k = 0
        while (curX <= drawWidth) {
            if (k % 5 == 0) {
                canvas.drawLine(curX, 0f, curX, drawHeight, mThickLinePaint)
            } else {
                canvas.drawLine(curX, 0f, curX, drawHeight, mLinePaint)
            }
            k++
            curX += GRID_WIDTH
        }
    }

    private var lastTouchX = 0f

    /**
     * 触摸事件
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!touchEnable) {
            return false
        }
        mVelocityTracker.addMovement(event)
        val touchRawX = event.rawX
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchX = touchRawX
            }
            MotionEvent.ACTION_MOVE -> {
                val delatX = touchRawX - lastTouchX
                var scrollerX = scrollX - delatX
                if (scrollerX < 0) {
                    scrollerX = 0f
                }

                if (scrollerX > drawWidth - width) {
                    scrollerX = drawWidth - width
                }
                Log.i("lpq", "onTouchEvent: scrollerX = $scrollerX")
                scrollTo(scrollerX.toInt(), 0)
            }
            MotionEvent.ACTION_UP -> {
                //根据速度和滑动距离判断是否翻页还是还原
                mVelocityTracker.computeCurrentVelocity(1000)
                val xVelocity = mVelocityTracker.xVelocity.toInt()
                if (Math.abs(xVelocity) >= 50) {
                    Log.i("lpq", "onTouchEvent: scrollX = $scrollX")
                    mOverScroller.fling(
                        scrollX, 0, -xVelocity, 0,
                        0, (drawWidth - width).toInt(), Int.MIN_VALUE, Int.MAX_VALUE
                    )
                    mVelocityTracker.clear()
                }
            }
        }
        lastTouchX = touchRawX
        return true
    }

    override fun computeScroll() {
        if (mOverScroller.computeScrollOffset()) {
            scrollTo(mOverScroller.currX, mOverScroller.currY)
            postInvalidate()
        }
    }

    override fun onDetachedFromWindow() {
        mVelocityTracker.recycle()
        super.onDetachedFromWindow()
    }

}