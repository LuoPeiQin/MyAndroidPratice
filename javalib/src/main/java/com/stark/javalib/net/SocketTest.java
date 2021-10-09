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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import okio.Buffer;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import okio.Source;

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
//        serverSocketTest();
        // NIO
//        nioServerSocketTest();
//        readFile("./javalib/text.txt");
//        copyFile("./javalib/text.txt", "./javalib/text2.txt");
        ByteString string = new ByteString(new byte[]{0x10, 0x20, 0x30});
        System.out.println(string.toString());
//        okioReadFile("./javalib/text.txt");
//        okioCopyFile("./javalib/text.txt", "./javalib/text3.txt");
    }

    private static void okioCopyFile(String srcFilePath, String outFilePath) {

        try (Source source = Okio.source(new File(srcFilePath));
             Sink sink = Okio.sink(new File(outFilePath));){
            Buffer buffer = new Buffer();
            long readLength;
            while ((readLength = source.read(buffer, 1024)) != -1) {
                sink.write(buffer, readLength);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void okioReadFile(String filePath) {
        try (Source source = Okio.source(new File(filePath));){
            Buffer buffer = new Buffer();
            while (source.read(buffer, 1024) != -1) {
                System.out.println(buffer.readString(Charset.defaultCharset()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFile(String srcFilePath, String outFilePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(srcFilePath)));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFilePath)))){
            String read;
            while ((read = reader.readLine()) != null) {
                writer.write(read);
                writer.write("\n");
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void readFile(String filePath) {
        System.out.println("开始读取文件" + filePath);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))){
            String content;
            while ((content = reader.readLine()) != null) {
                System.out.println(content);
            }
            System.out.println("文件读取完成" + filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void nioServerSocketTest() {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(80));
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.read(byteBuffer);
            byteBuffer.flip();
            System.out.println(Charset.defaultCharset().decode(byteBuffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    "Content-Length: 98\n" +
                    "\n" +
                    "＜html＞\n" +
                    "＜head＞\n" +
                    "＜title＞Wrox Homepage＜/title＞\n" +
                    "＜/head＞\n" +
                    "＜body＞\n" +
                    "＜/body＞\n" +
                    "＜/html＞\n\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
