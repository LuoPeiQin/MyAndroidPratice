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
 * @Description: 接力型多指触控
 * @Author: lpq
 * @CreateDate: 2021/11/19 11:11
 */
class MultiFingerImageView1(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getBitmap(context, 200.dp.toInt())
    private var offsetX = 0f
    private var offsetY = 0f
    private var downX = 0f
    private var downY = 0f
    private var originOffsetX = 0f
    private var originOffsetY = 0f
    private var curPointerId = 0

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val actionIndex = event.actionIndex
                curPointerId = event.getPointerId(actionIndex)
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
                originOffsetX = offsetX
                originOffsetY = offsetY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                val upPointerId = event.getPointerId(actionIndex)
                if (upPointerId == curPointerId) {
                    val newActionIndex = if (actionIndex == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    curPointerId = event.getPointerId(newActionIndex)
                    downX = event.getX(newActionIndex)
                    downY = event.getY(newActionIndex)
                    originOffsetX = offsetX
                    originOffsetY = offsetY
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val curIndex = event.findPointerIndex(curPointerId)
                offsetX = event.getX(curIndex) - downX + originOffsetX
                offsetY = event.getY(curIndex) - downY + originOffsetY
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, offsetX, offsetY, mPaint)
    }
}