package com.stark.javalib.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: alex.luo
 * @CreateDate: 2022/11/15
 */
public class ListUtils {
    public static <T extends Serializable> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    public static void main(String[] args) {
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            userList.add(new User("name" + i, i));
        }

        try {
            List<User> copyUserList = ListUtils.deepCopy(userList);
            System.out.println("打印原列表");
            printList(userList);
            System.out.println("打印复制表");
            printList(copyUserList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printList(List<User> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("index = " + i + " user = " + list.get(i) + " str = " + list.get(i).getString());
        }
    }
}
