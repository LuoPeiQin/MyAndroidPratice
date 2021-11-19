/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: Extension
 * Author: lyl
 * Date: 2021/10/20 20:06
 * Description: 用一句话描述下
 */
package com.stark.mypratice

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_DIP

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice
 * @ClassName: Extension
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/10/20 20:06
 */
val Float.dp
    get() = TypedValue.applyDimension(COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

val Int.dp
    get() = this.toFloat().dp

/**
 * 获取图片bitmap
 */
fun getBitmap(context: Context, width: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(context.resources, R.drawable.avatar, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(context.resources, R.drawable.avatar, options)
}