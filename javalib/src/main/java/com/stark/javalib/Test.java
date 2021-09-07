/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: Test
 * Author: lyl
 * Date: 2021/8/12 11:54
 * Description: 用一句话描述下
 */
package com.stark.javalib;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib
 * @ClassName: Test
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/8/12 11:54
 */
public class Test<T> {
    public static void main(String[] args) {
        int years = 5;
        double grow = 1.5;
        double result = Math.pow(grow, years);
        System.out.println("result = " + result);
    }

}
