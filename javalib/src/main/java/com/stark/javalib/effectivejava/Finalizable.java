package com.stark.javalib.effectivejava;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.javalib.effective
 * @ClassName: Finalizable
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/6/10 14:39
 */
public class Finalizable implements AutoCloseable {
    static AtomicInteger aliveCount = new AtomicInteger(0);

    Finalizable() {
        aliveCount.incrementAndGet();
    }

    @Override
    protected void finalize() {
        Finalizable.aliveCount.decrementAndGet();
        // 2、在finalize中抛出的异常将被系统隐藏，进而导致对象处于破损状态，程序可能出现不可预知的异常
        throw new RuntimeException("这个一个在finally中的错误");
    }

    public static void main(String args[]) {
        // 1、finalize的调用时机和是否一定调用是不可靠的，不要依赖在finalize中回收资源，以下代码将导致OOM
        for (int i = 0;; i++) {
            Finalizable f = new Finalizable();
            if ((i % 100_000) == 0) {
                System.out.format("After creating %d objects, %d are still alive.%n", i, Finalizable.aliveCount.get());
            }
        }
    }

    @Override
    public void close() throws Exception {

    }
}
