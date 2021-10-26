/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: PointView
 * Author: lyl
 * Date: 2021/10/26 13:31
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.stark.mypratice.dp

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.draw
 * @ClassName: PointView
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/26 13:31
 */
class PointView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var point = PointF(0f, 0f)
        set(value) {
            Log.i("lpq", "setValue: $value")
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(point.x, point.y, 10.dp,mPaint)
    }
}