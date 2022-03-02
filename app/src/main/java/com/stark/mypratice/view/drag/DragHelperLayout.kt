/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: DragHelperLayout
 * Author: lpq
 * Date: 2022/1/24 9:28
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.drag

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.customview.widget.ViewDragHelper

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.drag
 * @ClassName: DragHelperLayout
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/1/24 9:28
 */
private val ROW = 3
private val COLUMN = 2

class DragHelperLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    val viewDragHelper = ViewDragHelper.create(this, object : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            Log.i("lpq", "clampViewPositionHorizontal: left = $left")
            return left
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            Log.i("lpq", "clampViewPositionVertical: top = $top")
            return top
        }
    })

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return viewDragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        viewDragHelper.processTouchEvent(event)
        return true
    }


    override fun computeScroll() {
        if (viewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val childWidth = width / COLUMN
        val childHeight = height / ROW
        measureChildren(
            MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY)
        )
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val childWidth = width / COLUMN
        val childHeight = height / ROW
        children.forEachIndexed { index, view ->
            val row = index / COLUMN
            val column = index % COLUMN
            view.layout(
                column * childWidth,
                row * childHeight,
                (column + 1) * childWidth,
                (row + 1) * childHeight
            )
        }
    }

//    override fun onFinishInflate() {
////        children.forEach {
////            child
////        }
//    }


}