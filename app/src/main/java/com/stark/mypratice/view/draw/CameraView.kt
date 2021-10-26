package com.stark.mypratice.view.draw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.stark.mypratice.R
import com.stark.mypratice.dp

class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val BITMAP_WIDTH = 150.dp
    private val PADDING = 30.dp

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap = getBitmap(BITMAP_WIDTH.toInt())
    val camera = Camera()

    init {
        camera.setLocation(0f, 0f, -5 * resources.displayMetrics.density)
    }

    var degrees = 0f
        set(value) {
            field = value
            invalidate()
        }

    var rotatex = 0f
        set(value) {
            field = value
            invalidate()
        }

    var topRotateX = 0f
        set(value) {
            field = value
            invalidate()
        }


    override fun onDraw(canvas: Canvas) {
        Log.i("lpq", "onDraw: rotateX =  $rotatex  degrees = $degrees")
//        camera.rotateX(rotatex)
        // 场景3：斜着翻转
        // 画上半部分
        canvas.save()
        canvas.translate(PADDING + BITMAP_WIDTH / 2, PADDING + BITMAP_WIDTH / 2)
        canvas.rotate(-degrees)
        camera.save()
        camera.rotateX(topRotateX)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-BITMAP_WIDTH, -BITMAP_WIDTH, BITMAP_WIDTH, 0f)
        canvas.rotate(degrees)
        canvas.translate(-PADDING - BITMAP_WIDTH / 2, -PADDING - BITMAP_WIDTH / 2)
        canvas.drawBitmap(bitmap, PADDING, PADDING, mPaint)
        canvas.restore()
        // 画下半部翻转的位置
        canvas.save()
        canvas.translate(PADDING + BITMAP_WIDTH / 2, PADDING + BITMAP_WIDTH / 2)
        canvas.rotate(-degrees)
        camera.save()
        camera.rotateX(rotatex)
        camera.applyToCanvas(canvas)
        camera.restore()
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
//        camera.rotateX(rotatex)
//        camera.applyToCanvas(canvas)
//        camera.rotateX(-rotatex)
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