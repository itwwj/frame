package com.gitee.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁(写锁)/共享锁(读锁)/互斥锁
 * <p>
 * 独占锁：指该锁一次只能被一个线程所持有。对ReentrantLock和Synchronized而言都是独占锁
 * 共享锁：指该锁可被多个线程所持有。
 * <p>
 * <p>
 * 对ReentrantReadWriteLock其读锁是共享锁,其写锁是独占锁。
 * 读锁的共享锁可保证并发读是非常高效的,读写,写读,写写的过程是互斥的。
 *
 * @author jie
 */
public class MonopolizeLock {

    public static void main(String[] args) {
        MyCacheDb myCacheDb = new MyCacheDb();
        for (int i = 0; i < 5; i++) {
            final String axiba = i + "";
            new Thread(() -> myCacheDb.set(axiba, axiba)).start();
        }
        for (int i = 0; i < 5; i++) {
            final String axiba = i + "";
            new Thread(() -> myCacheDb.get(axiba)).start();
        }
    }
}

class MyCacheDb {

    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void set(String key, Object value) {
        lock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " write  " + key);
            Thread.sleep(1000);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " write over");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " read ");
            Thread.sleep(1000);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " read over  " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}