/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: CompableTest
 * Author: lyl
 * Date: 2021/9/4 15:20
 * Description: 用一句话描述下
 */
package com.stark.javalib.generic;

import com.stark.javalib.generic.fruit.Apple;

import java.io.Serializable;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.generic
 * @ClassName: CompableTest
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/9/4 15:20
 */
public class ComparableTest<T> implements Comparable<ComparableTest<T>> {
    @Override
    public int compareTo(ComparableTest<T> o) {
        return this == o ? 0 : 1;
    }

}
