/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: SportRing
 * Author: lyl
 * Date: 2021/10/22 14:05
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.draw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.stark.mypratice.dp

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.draw
 * @ClassName: SportRing
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/22 14:05
 */
class SportRing(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val RADIUS = 100.dp
    private val metrics = Paint.FontMetrics()
    private val bound = Rect()

    override fun onDraw(canvas: Canvas) {
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 12.dp
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.color = Color.GRAY
        // 画底环
        canvas.drawCircle(width/2f, height/2f, RADIUS, mPaint)
        // 画弧
        mPaint.color = Color.CYAN
        canvas.drawArc(width/2 - RADIUS, height/2 - RADIUS, width/2 + RADIUS, height/2 + RADIUS, -90f, 260f, false, mPaint)
        // 画文字
        mPaint.style = Paint.Style.FILL
        mPaint.color = Color.RED
        mPaint.textSize = 48.dp
        // 左右居中
        mPaint.textAlign = Paint.Align.CENTER
        // 上下居中
        var text = "abab"
        // 1、不随字体实际位置变化
        mPaint.getFontMetrics(metrics)
        Log.i("lpq", "onDraw: ${metrics.top} ${metrics.ascent} ${metrics.leading} ${metrics.descent} ${metrics.bottom}")
        canvas.drawText(text, width/2f , height/2f - (metrics.ascent + metrics.descent)/2, mPaint)
//        text = "qqqq"
//        mPaint.getFontMetrics(metrics)
//        Log.i("lpq", "onDraw: ${metrics.top} ${metrics.ascent} ${metrics.leading} ${metrics.descent} ${metrics.bottom}")
//        canvas.drawText(text, width/2f , height/2f - (metrics.ascent + metrics.descent)/2, mPaint)
        // 2、根据文字的实际大小来精确的确定位置（文字绘画位置会随不同的文字而变化，适合静态的文字）
//        mPaint.getTextBounds(text, 0, text.length, bound)
//        Log.i("lpq", "onDraw: ${bound.left} ${bound.top} ${bound.right} ${bound.bottom}")
//        canvas.drawText(text, width/2f, height/2f - (bound.top + bound.bottom)/2, mPaint)
//        text = "qqqq"
//        mPaint.getTextBounds(text, 0, text.length, bound)
//        Log.i("lpq", "onDraw: ${bound.left} ${bound.top} ${bound.right} ${bound.bottom}")
//        canvas.drawText(text, width/2f, height/2f - (bound.top + bound.bottom)/2, mPaint)

    }
}