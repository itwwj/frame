package com.gitee.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * Unsafe+CAS
 * 是指尝试获取锁的线程不会立即阻塞,而是采用循环的方式去尝试获取锁,
 * 这样的好处是减少线程上下文切换的消耗,缺点是循环会消耗CPU
 *
 * @author jie
 */
public class SpinLock {

    public static void main(String[] args) {

        SpinLock lock = new SpinLock();
        new Thread(()->{
            lock.myLock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.myUnLock();
        }).start();

        new Thread(()->{
            lock.myLock();
            lock.myUnLock();
        }).start();
    }



    /**
     * 原子引用线程
     */
    AtomicReference<Thread> reference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "come in");
        while (!reference.compareAndSet(null, thread)) {
        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "myUnLock");
    }
}
