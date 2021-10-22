/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: PieView
 * Author: lyl
 * Date: 2021/10/20 20:00
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.stark.mypratice.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.draw
 * @ClassName: PieView
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/20 20:00
 */
class PieView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val ANGLES = arrayOf(60f, 150f, 90f, 60f)
    val COLORS = arrayListOf(Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GREEN)

    private val RADIUS = 100.dp
    private val OFFSET = 10.dp

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        var startAngle = 0f
        for ((index, angle) in ANGLES.withIndex()) {
            if (index == 2) {
                canvas.save()
                canvas.translate(OFFSET * cos(Math.toRadians((startAngle + angle/2).toDouble())).toFloat(),
                    OFFSET * sin(Math.toRadians((startAngle + angle/2).toDouble())).toFloat()
                )
            }
            mPaint.setColor(COLORS[index])
            canvas.drawArc(width/2 - RADIUS, height/2 - RADIUS, width/2 + RADIUS, height/2 + RADIUS, startAngle, angle, true, mPaint)
            startAngle += angle
            if (index == 2) {
                canvas.restore()
            }
        }
    }
}