package com.stark.mypratice.view.drag

import android.content.ClipData
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import com.stark.mypratice.R

class DragToCollectLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    override fun onFinishInflate() {
        super.onFinishInflate()
        children.forEach {
            Log.i("lpq", "onFinishInflate: it.id = ${it.id}")
            Log.i("lpq", "onFinishInflate: R.id.iv1 = ${R.id.iv1}")
            Log.i("lpq", "onFinishInflate: R.id.iv2 = ${R.id.iv2}")
            if (it.id == R.id.iv1 || it.id == R.id.iv2) {
                it.setOnLongClickListener(object : OnLongClickListener {
                    override fun onLongClick(v: View): Boolean {
                        if (it.id == R.id.iv1) {
                            v.startDrag(
                                ClipData.newPlainText("1111", "111111"),
                                DragShadowBuilder(v),
                                null,
                                0
                            )
                        } else {
                            v.startDrag(
                                ClipData.newPlainText("2222", "222222"),
                                DragShadowBuilder(v),
                                null,
                                0
                            )
                        }
                        return true
                    }
                })
            }
            it.setOnDragListener(MyOnDragListener())
        }
    }

    class MyOnDragListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            if (event.action == DragEvent.ACTION_DROP) {
                if (v.id == R.id.textView) {
                    (v as TextView).text =
                        (v as TextView).text.toString() + event.clipData.getItemAt(0).text
                }
            }
            return true
        }

    }

}