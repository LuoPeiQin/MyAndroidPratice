/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: SquareView
 * Author: lyl
 * Date: 2021/10/29 16:22
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.layout
 * @ClassName: SquareView
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/29 16:22
 */
class SquareView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}