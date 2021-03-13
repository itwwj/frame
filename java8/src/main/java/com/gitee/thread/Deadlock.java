package com.gitee.thread;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 死锁是指两个或两个以上的进程在执行过程中,因争夺资源而造成的一种互相等待的现象,
 * 若E外力干涉那它们都将无法推进下去,如果系统资源充足,进程的资源请求都能够得到满足,
 * 死锁出现的可能性就很低,否则就会因争夺有限的资源而陷入死锁。
 *
 * @author jie
 */
public class Deadlock {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLock(lockA, lockB)).start();
        new Thread(new HoldLock(lockB, lockA)).start();

    }
}

@AllArgsConstructor
class HoldLock implements Runnable {
    private String lockA;
    private String lockB;

    @SneakyThrows
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "线程持有锁 " + lockA + " 尝试获取锁 " + lockB);
            TimeUnit.SECONDS.sleep(3);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "线程持有锁 " + lockB + " 尝试获取锁 " + lockA);
            }
        }
    }
}