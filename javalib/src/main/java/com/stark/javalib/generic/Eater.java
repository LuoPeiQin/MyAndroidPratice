package com.stark.javalib.generic;

import com.stark.javalib.generic.eat.Food;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.generic
 * @ClassName: Entar
 * @Description: 泛型测试类
 * @Author: lpq
 * @CreateDate: 2021/9/4 10:00
 */
interface Eater<T extends Food> {
    void eat(T food);
}
