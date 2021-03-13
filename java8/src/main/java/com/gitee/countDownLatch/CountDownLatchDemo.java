package com.gitee.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author jie
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
    }

    private static void countDown() throws InterruptedException {
        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 1; i <= count; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("count 已为0");
    }
}
