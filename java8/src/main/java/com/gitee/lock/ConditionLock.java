package com.gitee.lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 精确唤醒线程demo
 *
 * @author jie
 */
public class ConditionLock {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i=0;i<10;i++){
                shareResource.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i=0;i<10;i++){
                shareResource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i=0;i<10;i++){
                shareResource.print15();
            }
        },"C").start();
    }
}

class ShareResource {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (num != 1) {
                c1.await();

            }

            for (int i = 1; i < 6; i++) {
                System.out.println(Thread.currentThread().getName() + "    " + i);
            }
            num = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {

            while (num != 2) {
                c2.await();
            }
            for (int i = 1; i < 11; i++) {
                System.out.println(Thread.currentThread().getName() + "    " + i);
            }
            num = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {

            while (num != 3) {
                c3.await();
            }
            for (int i = 1; i < 16; i++) {
                System.out.println(Thread.currentThread().getName() + "    " + i);
            }
            num = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}