/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: CircleView
 * Author: lyl
 * Date: 2021/10/29 16:27
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.stark.mypratice.dp

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.layout
 * @ClassName: CircleView
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/29 16:27
 */

private val RADIUS = 100.dp
private val PADDING = 50.dp

class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = ((PADDING + RADIUS) * 2).toInt()
        val width = resolveSize(size, widthMeasureSpec)
        val height = resolveSize(size, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, mPaint)
    }
}