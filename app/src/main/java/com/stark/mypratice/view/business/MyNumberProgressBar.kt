/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: MyProgressBar
 * Author: lpq
 * Date: 2022/4/26 18:11
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.business

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.stark.mypratice.dp
import kotlin.math.PI

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.business
 * @ClassName: MyProgressBar
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/4/26 18:11
 */
class MyNumberProgressBar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val mBgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val rect = Rect()

    val M_PADDING_END = 6.dp
    private var progressHeight = 20.dp
    private var radius = progressHeight / 2

    var value = 0
    var max = 100

    init {
        mBgPaint.color = Color.parseColor("#F4F6F7")
        mPaint.color = Color.parseColor("#42D7C8")
        mTextPaint.color = Color.parseColor("#8A9199")
        mTextPaint.textSize = 9.dp
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        progressHeight = h.toFloat()
        radius = progressHeight / 2
    }

    override fun onDraw(canvas: Canvas) {
        drawBg(canvas)
        drawProgress(canvas)
        drawText(canvas)
    }

    /**
     * 画背景
     */
    private fun drawBg(canvas: Canvas) {
        canvas.drawRoundRect(0f, 0f, width.toFloat(), progressHeight, radius, radius, mBgPaint)
    }

    /**
     * 画进度
     */
    private fun drawProgress(canvas: Canvas) {
        val valueLength = value.toFloat() / max * width
        if (valueLength <= radius) {
            // 画弧度
            val angleA = Math.acos(((radius - valueLength) / radius).toDouble())
            Log.i("lpq", "drawProgress: $angleA")
            Log.i("lpq", "drawProgress: ${(180 - angleA * 180 / PI).toFloat()}")
            Log.i("lpq", "drawProgress: ${(angleA * 180 / PI * 2).toFloat()}")
            canvas.drawArc(
                0f,
                0f,
                2 * radius,
                progressHeight,
                (180 - angleA * 180 / PI).toFloat(),
                (angleA * 180 / PI * 2).toFloat(),
                false,
                mPaint
            )
        } else if (valueLength <= 2 * radius) {
            // 画弧度
            val angleA = Math.acos(((radius - valueLength) / radius).toDouble())
            Log.i("lpq", "drawProgress: $angleA")
            Log.i("lpq", "drawProgress: ${(180 - angleA * 180 / PI).toFloat()}")
            Log.i("lpq", "drawProgress: ${(angleA * 180 / PI * 2).toFloat()}")
            canvas.drawArc(
                0f, 0f, 2 * radius, progressHeight,
                90f, 180f, false, mPaint
            )
            canvas.drawRect(radius, 0f, valueLength, progressHeight, mPaint)
        } else {
            // 画圆角
            canvas.drawRoundRect(0f, 0f, valueLength, progressHeight, radius, radius, mPaint)
        }
    }

    /**
     * 画最大数值
     */
    private fun drawText(canvas: Canvas) {
        // 画max数值
        val maxStr = max.toString()
        mTextPaint.getTextBounds(maxStr, 0, maxStr.length, rect)
        val textWidth = rect.width()
        val textHeight = rect.height()
        val startX = width - M_PADDING_END - textWidth
        val startY = height / 2 + textHeight / 2
        canvas.drawText(maxStr, startX, startY.toFloat(), mTextPaint)
    }

}