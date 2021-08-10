package com.stark.javalib;

public class DeadLock {
    private Object lockA = new Object();
    private Object lockB = new Object();

    public static void main(String[] args) {
        new DeadLock().runTest();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runTest() {
        System.out.println("开始测试");
        new Thread(new Runnable() {
            @Override
            public void run() {
                workA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                workB();
            }
        }).start();
    }

    public void workA() {
        synchronized (lockA) {
            System.out.println("workA lockA");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println("workA lockB");
            }
        }
    }

    public void workB() {
        synchronized (lockB) {
            System.out.println("workB lockB");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockA) {
                System.out.println("workB lockA");
            }
        }
    }
}
