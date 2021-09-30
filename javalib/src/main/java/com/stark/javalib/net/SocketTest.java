/**
 * Copyright (C), 2007-2021, 未来穿戴有限公司
 * FileName: SocketTest
 * Author: lyl
 * Date: 2021/9/30 15:45
 * Description: 用一句话描述下
 */
package com.stark.javalib.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.net
 * @ClassName: SocketTest
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/9/30 15:45
 */
public class SocketTest {
    public static void main(String[] args) {
//        socketTest();
        serverSocketTest();
    }

    public static void socketTest() {
        try {
            Socket socket = new Socket("hencoder.com", 80);
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write("GET / HTTP/1.1\n");
            writer.write("Host: www.example.com\n\n");
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String readText;
            while ((readText = reader.readLine()) != null) {
                System.out.println(readText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serverSocketTest() {
        try (ServerSocket serverSocket = new ServerSocket(80);
        Socket socket = serverSocket.accept();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            System.out.println("sokcet = " + socket.getPort());
            writer.write("HTTP/1.1 200 OK\n" +
                    "Date: Sat, 31 Dec 2021 23:59:59 GMT\n" +
                    "Content-Type: text/html;charset=ISO-8859-1\n" +
                    "Content-Length: 122\n" +
                    "\n" +
                    "＜html＞\n" +
                    "＜head＞\n" +
                    "＜title＞Wrox Homepage＜/title＞\n" +
                    "＜/head＞\n" +
                    "＜body＞\n" +
                    "＜!-- body goes here --＞\n" +
                    "＜/body＞\n" +
                    "＜/html＞\n\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
