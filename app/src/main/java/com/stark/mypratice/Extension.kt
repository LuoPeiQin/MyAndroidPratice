/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: Extension
 * Author: lyl
 * Date: 2021/10/20 20:06
 * Description: 用一句话描述下
 */
package com.stark.mypratice

import android.content.res.Resources
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