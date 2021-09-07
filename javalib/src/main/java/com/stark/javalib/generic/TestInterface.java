package com.stark.javalib.generic;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.generic
 * @ClassName: TestInterface
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/9/4 15:35
 */
public class TestInterface {

    <T extends Runnable & Serializable> void someMethod(T param) {

    }

    public <T> void merge(T item, List<T> list) {
        list.add(item);
    }
}
