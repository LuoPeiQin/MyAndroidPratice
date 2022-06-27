/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: CaclMoney
 * Author: lpq
 * Date: 2022/6/27 18:34
 * Description: 用一句话描述下
 */
package com.stark.javalib.test;

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.test
 * @ClassName: CaclMoney
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/6/27 18:34
 */
public class CaclMoney {
    public static void main(String[] args) {
        int everyYear = 20;
        double everyYearRate = 1.2;
        double totalMoney = 50;
        int caclYears = 13;
        for (int i = 0; i < caclYears; ++i) {
            totalMoney = (totalMoney * everyYearRate) + everyYear;
            System.out.println("第" + i + "年的钱数：" + totalMoney);
        }
    }
}
