package com.stark.javalib.generic

import com.stark.javalib.generic.fruit.Fruit

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.generic
 * @ClassName: KotlinTest
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/9/7 9:20
 */

class KotlinTestClass<T> {
    fun testIn(t: T) {}
    fun testOut() : T {

        return null as T;
    }
}

interface KotlinTestInterface<T> {
    fun testIn(t:T)
    fun testOut() : T
}

class test<T> : KotlinTestInterface<Fruit> {
    override fun testIn(t: Fruit) {
    }

    override fun testOut(): Fruit {
        return null as Fruit
    }

}