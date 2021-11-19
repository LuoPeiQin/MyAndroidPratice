/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: MultiFingerImageView1
 * Author: lpq
 * Date: 2021/11/19 11:11
 * Description: 用一句话描述下
 */
package com.stark.mypratice.viewevent.multi_finger

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.stark.mypratice.dp
import com.stark.mypratice.getBitmap

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.viewevent.multi_finger
 * @ClassName: MultiFingerImageView1
 * @Description: 合作型多指触控
 * @Author: lpq
 * @CreateDate: 2021/11/19 11:11
 */
class MultiFingerImageView2(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getBitmap(context, 200.dp.toInt())
    private var offsetX = 0f
    private var offsetY = 0f
    private var downX = 0f
    private var downY = 0f
    private var originOffsetX = 0f
    private var originOffsetY = 0f
    private var focusX = 0f
    private var focusY = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var sumX = 0f
        var sumY = 0f
        for (i in 0 until event.pointerCount) {
            if (!(event.actionMasked == MotionEvent.ACTION_POINTER_UP && i == event.actionIndex)) {
                sumX += event.getX(i)
                sumY += event.getY(i)
            }
        }
        val pointerCount = if (event.actionMasked == MotionEvent.ACTION_POINTER_UP) {
            event.pointerCount - 1
        } else {
            event.pointerCount
        }
        focusX = sumX / pointerCount
        focusY = sumY / pointerCount
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                downX = focusX
                downY = focusY
                originOffsetX = offsetX
                originOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX = focusX - downX + originOffsetX
                offsetY = focusY - downY + originOffsetY
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, mPaint)
    }
}