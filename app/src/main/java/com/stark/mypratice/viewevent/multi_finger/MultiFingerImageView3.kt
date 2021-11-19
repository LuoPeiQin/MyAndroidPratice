/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: MultiFingerImageView3
 * Author: lpq
 * Date: 2021/11/19 14:18
 * Description: 用一句话描述下
 */
package com.stark.mypratice.viewevent.multi_finger

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.stark.mypratice.dp

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.viewevent.multi_finger
 * @ClassName: MultiFingerImageView3
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/11/19 14:18
 */
class MultiFingerImageView3(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paths = SparseArray<Path>()

    init {
        mPaint.color = Color.parseColor("#42D7C8")
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeWidth = 4.dp
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val path = Path()
                path.moveTo(event.getX(event.actionIndex), event.getY(event.actionIndex))
                paths.append(event.getPointerId(event.actionIndex), path)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until paths.size()) {
                    val pointerId = paths.keyAt(i)
                    val pointerIndex = event.findPointerIndex(pointerId)
                    paths.valueAt(i).lineTo(event.getX(pointerIndex), event.getY(pointerIndex))
                }
                invalidate()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                paths.remove(event.getPointerId(event.actionIndex))
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        for (i in 0 until paths.size()) {
            val path = paths.valueAt(i)
            canvas.drawPath(path, mPaint)
        }
    }
}