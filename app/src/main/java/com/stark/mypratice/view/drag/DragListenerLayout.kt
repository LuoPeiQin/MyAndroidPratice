/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: DragListenerLayout
 * Author: lpq
 * Date: 2022/1/24 9:28
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.drag

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.children

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.drag
 * @ClassName: DragListenerLayout
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/1/24 9:28
 */

private const val ROW = 3
private const val COLUMN = 2

class DragListenerLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var draggedView: View? = null
    private var orderedChildren: MutableList<View> = ArrayList()

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
            view.layout(0, 0, childWidth, childHeight)
            view.translationX = (column * childWidth).toFloat()
            view.translationY = (row * childHeight).toFloat()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        children.forEachIndexed { index, child ->
            orderedChildren.add(child)
            child.setOnLongClickListener(object : OnLongClickListener {
                override fun onLongClick(v: View?): Boolean {
                    draggedView = v
                    child.startDrag(null, DragShadowBuilder(v), v, 0)
                    return false
                }
            })
            child.setOnDragListener(dragListener)

        }
    }

    val dragListener = object : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            Log.i("lpq", "onDrag: v = ${v.id}  event.localState = ${(event.localState as View).id} event.action = ${event.action}")
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> if (v == event.localState) {
                    v.visibility = View.INVISIBLE
                }
                DragEvent.ACTION_DRAG_ENTERED -> if (v != event.localState) {
                    sort(v)
                }
                DragEvent.ACTION_DRAG_ENDED -> if (v == event.localState) {
                    v.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_EXITED -> {

                }

            }
            return true
        }

    }

    private fun sort(targetView: View) {
        var draggedIndex = -1
        var targetIndex = -1
        for ((index, child) in orderedChildren.withIndex()) {
            if (targetView === child) {
                targetIndex = index
            } else if (draggedView === child) {
                draggedIndex = index
            }
        }
        orderedChildren.removeAt(draggedIndex)
        orderedChildren.add(targetIndex, draggedView!!)
        var childLeft: Int
        var childTop: Int
        val childWidth = width / COLUMN
        val childHeight = height / ROW
        for ((index, child) in orderedChildren.withIndex()) {
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            child.animate()
                .translationX(childLeft.toFloat())
                .translationY(childTop.toFloat())
                .setDuration(150)
        }
    }

}