package com.stark.mypratice.view.draw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.stark.mypratice.dp

class XferModeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    override fun onDraw(canvas: Canvas) {
        val count = canvas.saveLayer(150.dp, 150.dp, 250.dp, 300.dp, null)
        mPaint.color = Color.RED
        canvas.drawCircle(200.dp, 200.dp, 50.dp, mPaint)
        mPaint.xfermode = porterDuffXfermode
        mPaint.color = Color.BLUE
        canvas.drawRect(100.dp, 200.dp, 200.dp, 300.dp, mPaint)
        mPaint.xfermode = null
        canvas.restoreToCount(count)
    }
}