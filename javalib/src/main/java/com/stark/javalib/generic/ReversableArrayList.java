/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: ReversableArrayList
 * Author: lyl
 * Date: 2021/9/4 10:07
 * Description: 用一句话描述下
 */
package com.stark.javalib.generic;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.generic
 * @ClassName: ReversableArrayList
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/9/4 10:07
 */
class ReversableArrayList<T> extends ArrayList<T> {
    public void reverse() {
        Collections.reverse(this);
    }
}
