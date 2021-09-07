/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: TestMain
 * Author: lyl
 * Date: 2021/9/4 10:54
 * Description: 用一句话描述下
 */
package com.stark.javalib.generic;

import com.stark.javalib.Test;
import com.stark.javalib.generic.eat.Meat;
import com.stark.javalib.generic.fruit.Apple;
import com.stark.javalib.generic.fruit.Fruit;
import com.stark.javalib.generic.typeerasure.TypeErasureTest;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.generic
 * @ClassName: TestMain
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/9/4 10:54
 */

//        ArrayList<? extends Fruit> fruits = new ArrayList<Apple>();
//        Fruit a = fruits.get(0);
//
//        ComparableTest<Fruit> fruitComparableTest = new ComparableTest<>();
//        fruitComparableTest.compareTo(fruitComparableTest);

public class TestMain {
    public static void main(String[] args) {
        // 泛型擦除
        Meat<*> meat = new Meat<String>();

    }
}
