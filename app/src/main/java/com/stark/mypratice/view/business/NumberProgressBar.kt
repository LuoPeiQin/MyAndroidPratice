/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: NumberProgressBar
 * Author: lpq
 * Date: 2022/4/15 14:36
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.business

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.ProgressBar
import com.stark.mypratice.dp

/**
 *
 * @ProjectName: SKG
 * @Package: com.skg.business.view
 * @ClassName: NumberProgressBar
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/4/15 14:36
 */
class NumberProgressBar(context: Context?, attrs: AttributeSet?) : ProgressBar(context, attrs) {

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val M_PADDING_END = 6.dp
    val rect = Rect()

    init {
        mPaint.color = Color.parseColor("#8A9199")
        mPaint.textSize = 9.dp
    }

    override fun onDrawForeground(canvas: Canvas) {
        super.onDrawForeground(canvas)
        // 画max数值
        val maxStr = max.toString()
        mPaint.getTextBounds(maxStr, 0, maxStr.length, rect)
        val textWidth = rect.width()
        val textHeight = rect.height()
        val startX = width - M_PADDING_END - textWidth
        val startY = height - textHeight / 2
        canvas.drawText(maxStr, startX, startY.toFloat(), mPaint)
    }

//    override fun onDraw(canvas: Canvas) {
//        canvas.draw
//    }
}