/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: CircleView
 * Author: lyl
 * Date: 2021/10/26 11:04
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.stark.mypratice.dp

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.draw
 * @ClassName: CircleView
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/26 11:04
 */
class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.CYAN
    }
    private var radius = 10.dp
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width/2f, height/2f, radius, mPaint)
    }
}