package com.stark.mypratice.view.layout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

class TagLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private val boundList = mutableListOf<Rect>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 画单行没有问题的Layout
        // 1、计算每一个子view的应该位置
        // 2、计算layout总共占用了多少宽度和高度
        // 3、告诉父view自己总共用了多少宽高
        val widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec)
        var widthUsed = 0
        var heightUsed = 0
        var lineWidthUsed = 0
        var lineHeightUsed = 0
        for((index, view) in children.withIndex()) {
            measureChildWithMargins(view, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            // 需要换行的情况
            if (lineWidthUsed + view.measuredWidth > widthMeasureSize) {
                lineWidthUsed = 0
                heightUsed += lineHeightUsed
                lineHeightUsed = 0
                measureChildWithMargins(view, widthMeasureSpec, lineWidthUsed, heightMeasureSpec, heightUsed)
            }
            if (boundList.size <= index) {
                boundList.add(index, Rect())
            }
            val bound = boundList[index]
            bound.set(lineWidthUsed, heightUsed, lineWidthUsed + view.measuredWidth, heightUsed + view.measuredHeight)

            lineWidthUsed += view.measuredWidth
            lineHeightUsed = max(lineHeightUsed, view.measuredHeight)

            widthUsed = max(widthUsed, lineWidthUsed)
            Log.i("lpq", "onMeasure: widthUsed = $widthUsed heightUsed = $heightUsed lineWidthUsed = $lineWidthUsed lineHeightUsed = $lineHeightUsed")
        }
        heightUsed += lineHeightUsed
        setMeasuredDimension(widthUsed, heightUsed)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        // 1、使用给子View测量出来的位置，进行layout
        for((index, view) in children.withIndex()) {
            val bound = boundList[index]
            view.layout(bound.left, bound.top, bound.right, bound.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}