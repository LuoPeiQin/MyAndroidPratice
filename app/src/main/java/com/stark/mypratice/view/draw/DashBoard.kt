/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: DashBoard
 * Author: lyl
 * Date: 2021/10/20 20:00
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.draw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.stark.mypratice.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.draw
 * @ClassName: DashBoard
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/20 20:00
 */
class DashBoard(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val SPACE_NUMBER = 20
    private val ANGLE = 120f
    val startAngle = ANGLE / 2 + 90f
    val sweepAngle = 360 - ANGLE
    private val RADIUS = 100.dp
    private val POINTER_LENGTH = RADIUS * 0.7
    private var centerX = 0f
    private var centerY = 0f

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val path = Path()
    val pathDash = Path()
    val pathMeasure = PathMeasure()
    lateinit var pathDashPathEffect: PathDashPathEffect
    init {
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 3.dp
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = width / 2f
        centerY = height / 2f
        path.addArc(centerX - RADIUS, centerY - RADIUS, centerX + RADIUS, centerY + RADIUS, startAngle, sweepAngle)
        pathMeasure.setPath(path, false)
        val advance = (pathMeasure.length - 2.dp) / SPACE_NUMBER
        val phase = 0f
        pathDash.addRect(0f, 0f, 2.dp, 5.dp, Path.Direction.CCW)
        pathDashPathEffect = PathDashPathEffect(pathDash, advance, phase, PathDashPathEffect.Style.ROTATE)

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, mPaint)
        mPaint.setPathEffect(pathDashPathEffect)
        canvas.drawPath(path, mPaint)
        mPaint.setPathEffect(null)
        drawPointer(canvas, 10)
    }

    fun drawPointer(canvas: Canvas, index: Int) {
        canvas.drawLine(centerX, centerY, centerX + (POINTER_LENGTH * cos(getRadian(index))).toFloat(),
            centerY + (POINTER_LENGTH * sin(getRadian(index))).toFloat(), mPaint)
    }

    fun getRadian(index: Int): Double {
        return Math.toRadians((startAngle + sweepAngle/SPACE_NUMBER*index).toDouble())
    }
}