/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: TextLayoutView
 * Author: lyl
 * Date: 2021/10/22 14:49
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.draw

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.stark.mypratice.R
import com.stark.mypratice.dp

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.draw
 * @ClassName: TextLayoutView
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/22 14:49
 */
class TextLayoutView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
            " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an " +
            "unknown printer took a galley of type and scrambled it to make a type specimen book. It " +
            "has survived not only five centuries, but also the leap into electronic typesetting, " +
            "remaining essentially unchanged. It was popularised in the 1960s with the release of " +
            "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop " +
            "publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    private val BITMAP_WIDTH = 100.dp
    private val BITMAP_PADDING = 50.dp
    private val fontMetrics = Paint.FontMetrics()
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val bitmap = getBitmap(BITMAP_WIDTH.toInt())
    init {
        mPaint.textSize = 16.dp
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, width - BITMAP_WIDTH, BITMAP_PADDING, mPaint)
        // 使用普通的方式绘制文本(一行一行的)加图片
        mPaint.getFontMetrics(fontMetrics)
        var start = 0
        var count = 0
        var offset = -fontMetrics.top
        var w = 0f
        while (start < text.length) {
            if (offset + fontMetrics.bottom < BITMAP_PADDING || offset + fontMetrics.top > BITMAP_PADDING + BITMAP_WIDTH) {
                w = width.toFloat()
            } else {
                w = width - BITMAP_WIDTH
            }
            count = mPaint.breakText(text, start, text.length,true, w, null)
            canvas.drawText(text, start, start + count, 0f, offset, mPaint)
            start += count
            offset += mPaint.fontSpacing
        }

        // 使用 StaticLayout绘制图片加文字
//        val staticLayout = StaticLayout(text, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false)
//        staticLayout.draw(canvas)
//        canvas.drawBitmap(bitmap, width - BITMAP_WIDTH, BITMAP_PADDING, mPaint)
    }

    fun getBitmap(width: Int) : Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.logo, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.logo, options)

    }
}