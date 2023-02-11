/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: Test
 * Author: lyl
 * Date: 2021/8/12 11:54
 * Description: 用一句话描述下
 */
package com.stark.javalib;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib
 * @ClassName: Test
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/8/12 11:54
 */
public class Test {
    public static void main(String[] args) {
        // String regex = "21\\d\\d";
        // System.out.println("2019".matches(regex)); // true
        // System.out.println("2100".matches(regex)); // false
        //
        // String re1 = "abc";
        // System.out.println("abc".matches(re1));
        // System.out.println("Abc".matches(re1));
        // System.out.println("abcd".matches(re1));
        //
        // String re2 = "a\\Sc"; // 对应的正则是a\&c
        // System.out.println("a&c".matches(re2));
        // System.out.println("a-c".matches(re2));
        // System.out.println("a1c".matches(re2));
        // System.out.println("a&&c".matches(re2));
        //
        // String re3 = "[^0-24-7]\\d{7,8}";
        // System.out.println("32345678".matches(re3));
        // System.out.println("221235678".matches(re3));
        // System.out.println("422235678".matches(re3));

        // 创建一个 HashMap
        ConcurrentHashMap<Integer, String> sites = new ConcurrentHashMap<>();

        // 往 HashMap 添加一些元素
        sites.put(2, "Runoob");
        sites.put(1, "Google");
        sites.put(6, "Google");
        sites.put(3, "Google");
        sites.put(5, "Google");
        sites.put(33, "Taobao");
        sites.put(33, "Taao");
        sites.put(10, "Google");
        System.out.println("sites HashMap: " + sites);

        sites.remove(5);
        sites.remove(2);

        // 返回所有 key 组成的 set 集合视图
        System.out.println("Keys: " + sites.keySet());
        // keySet() 返回所有 key 组成的 set 视图
        // for-each loop 在该视图中访问每一个 key
        for(int key: sites.keySet()) {

            // 输出每个 key
            System.out.print(key + ", ");
        }
    }

    private static void test1() {
        String s = "the quick brown fox jumps over the lazy dog.";
        Pattern p = Pattern.compile("\\wo\\w");
        Matcher m = p.matcher(s);
        while (m.find()) {
            String sub = s.substring(m.start(), m.end());
            System.out.println(sub);
        }
    }

    public static void test() {
        String re = "\\d{3,4}-\\d{7,8}";
        for (String s : List.of("010-12345678", "020-9999999", "0755-7654321")) {
            if (!s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        for (String s : List.of("010 12345678", "A20-9999999", "0755-7654.321")) {
            if (s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        System.out.println("测试成功!");
    }

}
