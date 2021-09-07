/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: Test
 * Author: lyl
 * Date: 2021/9/6 19:58
 * Description: 用一句话描述下
 */
package com.stark.javalib.generic.typeerasure;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.generic.typeerasure
 * @ClassName: Test
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/9/6 19:58
 */
public class TypeErasureTest {
    public List<String> list1 = new ArrayList<>();
    public List<String> list2 = new ArrayList<String>(){};

    public static void main(String[] args) {

        // 泛型擦除
        TypeErasureTest t = new TypeErasureTest();
        try {
            Field field1 = t.getClass().getField("list1");
            System.out.println("" + field1.getGenericType());
            Field field2 = t.getClass().getField("list2");
            System.out.println("" + field2.getGenericType().toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
