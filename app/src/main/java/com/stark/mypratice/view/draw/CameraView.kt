package com.stark.mypratice.view.draw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.stark.mypratice.R
import com.stark.mypratice.dp

private val BITMAP_WIDTH = 200.dp
private val PADDING = 80.dp

class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap = getBitmap(BITMAP_WIDTH.toInt())
    val camera = Camera()
    init {
        camera.rotateX(20f)
        camera.setLocation(0f, 0f,  -5 * resources.displayMetrics.density)
    }


    override fun onDraw(canvas: Canvas) {
        // 场景3：斜着翻转
        val degrees = 30f
        // 画上半部分
        canvas.save()
        canvas.translate(PADDING + BITMAP_WIDTH / 2, PADDING + BITMAP_WIDTH / 2)
        canvas.rotate(-degrees)
        canvas.clipRect(-BITMAP_WIDTH, -BITMAP_WIDTH, BITMAP_WIDTH, 0f)
        canvas.rotate(degrees)
        canvas.translate(-PADDING - BITMAP_WIDTH / 2, -PADDING - BITMAP_WIDTH / 2)
        canvas.drawBitmap(bitmap, PADDING, PADDING, mPaint)
        canvas.restore()
        // 画下半部翻转的位置
        canvas.save()
        canvas.translate(PADDING + BITMAP_WIDTH / 2, PADDING + BITMAP_WIDTH / 2)
        canvas.rotate(-degrees)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-BITMAP_WIDTH, 0f, BITMAP_WIDTH, BITMAP_WIDTH)
        canvas.rotate(degrees)
        canvas.translate(-PADDING - BITMAP_WIDTH / 2, -PADDING - BITMAP_WIDTH / 2)
        canvas.drawBitmap(bitmap, PADDING, PADDING, mPaint)
        canvas.restore()

        // 场景2：一半翻转，一半不翻转
//        // 画上半部分
//        canvas.save()
//        canvas.translate(PADDING + BITMAP_WIDTH / 2, PADDING + BITMAP_WIDTH / 2)
//        canvas.clipRect(-BITMAP_WIDTH/2, -BITMAP_WIDTH/2, BITMAP_WIDTH/2, 0f)
//        canvas.translate(-PADDING - BITMAP_WIDTH / 2, -PADDING - BITMAP_WIDTH / 2)
//        canvas.drawBitmap(bitmap, PADDING, PADDING, mPaint)
//        canvas.restore()
//        // 画下半部翻转的位置
//        canvas.save()
//        canvas.translate(PADDING + BITMAP_WIDTH / 2, PADDING + BITMAP_WIDTH / 2)
//        camera.applyToCanvas(canvas)
//        canvas.clipRect(-BITMAP_WIDTH/2, 0f, BITMAP_WIDTH/2, BITMAP_WIDTH/2)
//        canvas.translate(-PADDING - BITMAP_WIDTH / 2, -PADDING - BITMAP_WIDTH / 2)
//        canvas.drawBitmap(bitmap, PADDING, PADDING, mPaint)
//        canvas.restore()

        // 场景1：延X翻转30度
//        canvas.translate(PADDING + BITMAP_WIDTH / 2, PADDING + BITMAP_WIDTH / 2)
//        camera.applyToCanvas(canvas)
//        canvas.translate(-PADDING - BITMAP_WIDTH / 2, -PADDING - BITMAP_WIDTH / 2)
//        canvas.drawBitmap(bitmap, PADDING, PADDING, mPaint)

    }

    private fun getBitmap(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }
}