package com.gitee.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁/递归锁
 * <p>
 * 指的是同一线程外层函数获得锁之后,内层递归函数仍然能获取该锁的代码,
 * 在同一个线程在外层方法获取锁的时候,在进入内层方法会自动获取锁
 * 也即是说,线程可以进入任何一个它已经拥有的锁所同步着的代码块。
 * <p>
 * <p>
 * ReentrantLock/synchronized典型的可重入锁
 * <p>
 * <p>
 * 可重入锁最大的作用就是避免死锁
 *
 * @author jie
 */
public class ReentryLock {
    public static void main(String[] args) throws InterruptedException {
        Cat cat = new Cat();

        new Thread(() -> cat.say()).start();

        new Thread(() -> cat.runing()).start();


        Thread.sleep(1);

        System.out.println();

        new Thread(new Cat()).start();

        new Thread(new Cat()).start();
    }
}

class Cat implements Runnable {

    public synchronized void say() {
        System.out.println(Thread.currentThread().getName() + "    say");
        runing();
    }

    public synchronized void runing() {
        System.out.println(Thread.currentThread().getName() + "    run");
    }

    //============================================================================================


    Lock lock = new ReentrantLock();

    public void jump() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "    jump");
            eat();
        } finally {
            lock.unlock();
        }
    }

    public void eat() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "    eat");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        jump();
    }
}