package com.stark.mypratice.view.draw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.stark.mypratice.R
import com.stark.mypratice.dp

class AvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap : Bitmap = getBitmap(100.dp.toInt())

    override fun onDraw(canvas: Canvas) {
        val count = canvas.saveLayer(50.dp, 50.dp, 150.dp, 150.dp, null)
        canvas.drawCircle(100.dp, 100.dp, 50.dp, mPaint)
        mPaint.xfermode = porterDuffXfermode
        canvas.drawBitmap(bitmap, 50.dp, 50.dp, mPaint)
        mPaint.xfermode = null
        canvas.restoreToCount(count)
    }

    private fun getBitmap(width: Int) : Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    }


}