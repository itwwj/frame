package com.gitee.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁与非公平锁
 * <p>
 * 公平锁,就是很公平,在并发环境中,每个线程在获取镇时会先查看此锁维护的等待队列,如果为空,
 *   或者当前线程是等待队列的第一个,就占有锁,否则就会加入到等待队列中,以后会按照FIFO的规则从队列中取到自己
 * <p>
 * 非公平锁，上来就直接尝试占有锁,如果尝试失败,就再采用类似公平锁那种方式。
 *
 *
 *
 *
 * @author jie
 */
public class EquitableLock {
    public static void main(String[] args) {
        //非公平锁
        ReentrantLock reentrantLock = new ReentrantLock();
        //公平锁
        ReentrantLock equitableLock = new ReentrantLock(true);
    }
}
