/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: ContentTextView
 * Author: lyl
 * Date: 2021/10/26 17:14
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.draw

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.stark.mypratice.dp

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.draw
 * @ClassName: ContentTextView
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/26 17:14
 */
class ContentTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
        textAlign = Paint.Align.CENTER
    }

    private val textList = arrayListOf<String>(
        "福建",
        "101",
        "102",
        "103",
        "104",
        "105",
        "106",
        "107",
        "108",
        "109",
        "1010",
        "1011",
        "1012",
        "1013",
        "1014",
        "1015",
        "1016",
        "1017",
        "1018",
        "1019",
        "10200",
        "10201",
        "10202",
        "10203",
        "10204",
        "10205",
        "10206",
        "10207",
        "10208",
        "10209",
    )

    var text = "福建"
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText(text, width / 2f, height / 2f, mPaint)
    }

    val stringType = object : TypeEvaluator<String> {
        override fun evaluate(fraction: Float, startValue: String, endValue: String): String {
            val curIndex = (textList.indexOf(endValue) - textList.indexOf(startValue)) * fraction
            return textList[curIndex.toInt()]
        }

    }
}