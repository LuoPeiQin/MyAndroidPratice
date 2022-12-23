package com.stark.javalib.utils;

import java.io.Serializable;

/**
 * @Description:
 * @Author: alex.luo
 * @CreateDate: 2022/11/15
 */
public class User implements Serializable {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
