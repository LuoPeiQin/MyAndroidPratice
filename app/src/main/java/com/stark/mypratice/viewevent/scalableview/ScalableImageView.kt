/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: ScalableImageView
 * Author: lyl
 * Date: 2021/11/11 19:41
 * Description: 用一句话描述下
 */
package com.stark.mypratice.viewevent.scalableview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.stark.mypratice.R
import com.stark.mypratice.dp
import kotlin.math.max
import kotlin.math.min

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.viewevent.scalableview
 * @ClassName: ScalableImageView
 * @Description: 可缩放、移动的ImageView
 * 需要实现的效果
 * 1、双击缩放；
 * 2、双击缩放过程有动画
 * 3、放大后可以移动图片
 * 4、移动图片时有边界
 * 5、快速滑动实现
 * 6、快速滑动的over边界效果
 * 7、根据手指点击的位置缩放
 * 8、双指捏撑进行放大和缩小
 * 9、根据双指捏撑的焦点进行放大和缩小
 * @Author: lpq
 * @CreateDate: 2021/11/11 19:41
 */
private val IMAGE_WIDTH = 200.dp
private var IMAGE_HEIGHT = 200.dp

class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs),
    GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private val bitmap = getBitmap(IMAGE_WIDTH.toInt())
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var smallScale = 0f
    private var bigScale = 0f
    private var scaleModulus = 1.5f
    private var isBig = false
    private var originOffsetX = 0f
    private var originOffsetY = 0f
    private var maxOffsetX = 0f
    private var maxOffsetY = 0f
    private val gestureDetectorCompat = GestureDetectorCompat(context, this).apply {
        setOnDoubleTapListener(this@ScalableImageView)
    }
    private var fractionScale = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var scaleAnimator = ObjectAnimator.ofFloat(this, "fractionScale", 0f, 1f)

    init {
        IMAGE_HEIGHT = bitmap.height.toFloat()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return super.onTouchEvent(event)
        return gestureDetectorCompat.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (h / IMAGE_HEIGHT > w / IMAGE_WIDTH) {
            smallScale = w / IMAGE_WIDTH
            bigScale = h / IMAGE_HEIGHT
        } else {
            smallScale = h / IMAGE_HEIGHT
            bigScale = w / IMAGE_WIDTH
        }
        maxOffsetX = ((IMAGE_WIDTH * bigScale) * scaleModulus - w) / 2
        maxOffsetY = ((IMAGE_WIDTH * bigScale) * scaleModulus - h) / 2
    }

    override fun onDraw(canvas: Canvas) {
        canvas.translate(
            originOffsetX * fractionScale,
            originOffsetY * fractionScale
        )
        val scale = smallScale + (bigScale * scaleModulus - smallScale) * fractionScale
        canvas.scale(scale, scale, width / 2f, height / 2f)
        canvas.drawBitmap(
            bitmap,
            width / 2f - IMAGE_WIDTH / 2,
            height / 2 - IMAGE_WIDTH / 2,
            mPaint
        )
    }

    private fun getBitmap(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar, options)
    }

    /**
     * 按下事件：需要返回true才能收到后续事件
     */
    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    /**
     * 按下状态：
     * 1、在可滑动组件中，要在100ms的延迟后才会调用该方法
     * 2、在不可滑动组件中，在down时间后就会调用该方法
     */
    override fun onShowPress(e: MotionEvent?) {
    }

    /**
     * 点击时被调用（支持长按时，长按后松手不会被调用，双击的第二下时不会被调用）
     */
    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    /**
     * 用户滑动时被调用
     * down时间之后，该时间被调用
     * 时间是按下时的事件和当前的事件
     * 偏移时前一次和当前位置的偏移
     */
    override fun onScroll(
        downEvent: MotionEvent?,
        curEvent: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        if (isBig) {
            originOffsetX -= distanceX
            originOffsetY -= distanceY
            originOffsetX = min(originOffsetX, maxOffsetX)
            originOffsetX = max(originOffsetX, -maxOffsetX)
            originOffsetY = min(originOffsetY, maxOffsetY)
            originOffsetY = max(originOffsetY, -maxOffsetY)
            invalidate()
        }
        return false
    }

    /**
     * 用户长按（500ms）时被触发
     */
    override fun onLongPress(e: MotionEvent?) {
    }

    private val overScroller = OverScroller(context)

    /**
     * 用户滑动时迅速抬起时被调用，用于用户希望控件进行惯性滑动的场景
     */
    override fun onFling(
        downEvent: MotionEvent?,
        curEvent: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.i("lpq", "onFling: velocityX = $velocityX velocityY = $velocityY")
        if (isBig) {
            overScroller.fling(
                originOffsetX.toInt(),
                originOffsetY.toInt(),
                velocityX.toInt(),
                velocityY.toInt(),
                -maxOffsetX.toInt(),
                maxOffsetX.toInt(),
                -maxOffsetY.toInt(),
                maxOffsetY.toInt(),
                20.dp.toInt(),
                20.dp.toInt()
            )
            postOnAnimation(flingRunnable)
        }
        return false
    }

    private val flingRunnable = FlingRunnable()

    inner class FlingRunnable : Runnable {
        override fun run() {
            val isRunning = overScroller.computeScrollOffset()
            if (isRunning) {
                originOffsetX = overScroller.currX.toFloat()
                originOffsetY = overScroller.currY.toFloat()
                invalidate()
                postOnAnimation(this)
            }
        }

    }

    /**
     * 用户单击时被调用
     * 和onSingleTap的区别在于，需要在抬起后300ms内没有down事件才会被调用
     */
    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return false
    }

    /**
     * 双击之后被调用，两次点击down事件
     */
    override fun onDoubleTap(e: MotionEvent): Boolean {
        isBig = !isBig
        if (isBig) {
            val offsetScale = (bigScale * scaleModulus - smallScale) / 2
            var x = offsetScale * (e.x - width / 2)
            var y = offsetScale * (e.y - height / 2)
            x = min(x, maxOffsetX)
            x = max(x, -maxOffsetX)
            y = min(y, maxOffsetY)
            y = max(y, -maxOffsetY)
            originOffsetX = -x
            originOffsetY = -y
            scaleAnimator.start()
        } else {
            scaleAnimator.reverse()
        }
        return false
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return false
    }
}