package com.stark.javalib.designpatterns;

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.designpatterns
 * @ClassName: ThreadSingleton_3
 * @Description: 线程单例测试实现
 * @Author: lpq
 * @CreateDate: 2022/7/1 15:22
 */
public class ThreadSingleton_3 {

    public static void main(String[] args) {
//        testThreadLocal();
        testThreadSingleton();
    }

    /**
     * 测试线程唯一的单例
     */
    private static void testThreadSingleton() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 threadSingleton = " + ThreadSingleton.getInstance());
                System.out.println("thread1 threadSingleton = " + ThreadSingleton.getInstance());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2 threadSingleton = " + ThreadSingleton.getInstance());
                System.out.println("thread2 threadSingleton = " + ThreadSingleton.getInstance());
            }
        }).start();
    }

    /**
     * 验证ThreadLocal对线程存储的隔离
     */
    private static void testThreadLocal() {
        final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 value = " + threadLocal.get());
                threadLocal.set(5);
                System.out.println("thread1 value = " + threadLocal.get());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2 value = " + threadLocal.get());
                threadLocal.set(8);
                System.out.println("thread2 value = " + threadLocal.get());
            }
        }).start();
    }

}

/**
 * 线程单例类
 */
class ThreadSingleton {
    private static final ThreadLocal<ThreadSingleton> threadLocal = new ThreadLocal<>();

    private ThreadSingleton() {}

    public static ThreadSingleton getInstance() {
        ThreadSingleton threadSingleton = threadLocal.get();
        if (threadSingleton == null) {
            threadSingleton = new ThreadSingleton();
            threadLocal.set(threadSingleton);
        }
        return threadSingleton;
    }
}
