/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: CaclMoney
 * Author: lpq
 * Date: 2022/6/27 18:34
 * Description: 用一句话描述下
 */
package com.stark.javalib.test;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.test
 * @ClassName: CaclMoney
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/6/27 18:34
 */
public class CaclMoney {
    public static void main(String[] args) {
        CaclMoney caclMoney = new CaclMoney();
        caclMoney.caclInvestMoney(10);
        caclMoney.caclBuyHomeMoney(10);
//        double baseRate = 1.1;
//        double total = 1;
//        for (int i = 0; i < 10; i++) {
//            total = total * baseRate;
//            System.out.println("total = " + total);
//        }
    }

    public void caclInvestMoney(int caclYears) {
        System.out.println("投资资产计算");
        int everyYear = 30;
        double everyYearRate = 1.25;
        double totalMoney = 60;
        for (int i = 0; i < caclYears; ++i) {
            totalMoney = (totalMoney * everyYearRate) + everyYear;
//            if (everyYear <= 50) {
//                everyYear += 10;
//            }
            System.out.println("第" + (i + 2022) + "年底的钱数：" + totalMoney);
        }
    }

    public void caclBuyHomeMoney(int caclYears) {
        System.out.println("买房资产计算");
        double homeMoney = 400;
        double everyYearRate = 1.25;
        double totalMoney = 60;
        int everyYear = 30;
        double everyMonthMoney = 1.4;
        double firstMoney = 120;
        totalMoney = totalMoney - firstMoney;
        for (int i = 0; i < caclYears; ++i) {
            double everyYearLave = everyYear - (everyMonthMoney * 12);
            totalMoney += everyYearLave;
            if (totalMoney > 0) {
                totalMoney = totalMoney * everyYearRate;
            }
//            if (everyYear <= 50) {
//                everyYear += 10;
//            }
            homeMoney = homeMoney * 1.05;
            System.out.println("第" + (i + 2022) + "年底的钱数：" + totalMoney + "  房产净价值： " + (homeMoney - 400 + 120));
        }

    }
}
