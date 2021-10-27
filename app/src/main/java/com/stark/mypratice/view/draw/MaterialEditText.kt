/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: MaterialEditText
 * Author: lyl
 * Date: 2021/10/27 17:07
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.draw

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.stark.mypratice.dp

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.draw
 * @ClassName: MaterialEditText
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/27 17:07
 */

private val TIP_TEXT_SIZE = 12.dp
private val TIP_TEXT_PADDING = 6.dp
private val TIP_TOP_PADDING = 8.dp
private val ANIMATION_PADDING = 16.dp

class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var isTopShow = false
    private val animator: ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(this, "fraction", 1f)
    }

    var fraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        mPaint.textSize = TIP_TEXT_SIZE
        setPadding(
            paddingLeft,
            (paddingTop + TIP_TEXT_SIZE + TIP_TEXT_PADDING).toInt(), paddingRight, paddingBottom
        )
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (text.isNullOrEmpty() && isTopShow) {
            isTopShow = false
            animator.reverse()
        } else if (!text.isNullOrEmpty() && !isTopShow){
            isTopShow = true
            animator.start()
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.alpha = (fraction * 0xff).toInt()
        canvas.drawText(hint.toString(), paddingLeft.toFloat(), TIP_TOP_PADDING + TIP_TEXT_SIZE + ANIMATION_PADDING * (1-fraction), mPaint)
    }
}