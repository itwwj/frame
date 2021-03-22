package com.gitee.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 * 用于创建锁和其他同步类的基本线程阻塞原语
 * 该类与使用它的每个线程关联一个许可证(在Senaphore类的意义上) ,如果许可证可用,将立即返回park,
 * 并在此过程中消费;否则可能会阻止,如果尚未提供许可,则致电unpark获得许可. (与Semaphores不同,许可证不会累祝。最多只有一个. )
 * 可靠的使用需要使用volatile (或原子)变量来控制何时停放或取消停放。对于易失性变量访问保持对这些方法的调用的顺序,但不一定是非易失性变量访问。
 * <p>
 * <p>
 * wait和notify的加强版
 *
 * @author jie
 */
public class LockSupportDemo {
    /**
     * 先唤醒后等待 等待会不起作用
     * @param args
     */
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            System.out.println("进入线程a");
            LockSupport.park();
            System.out.println("a线程唤醒");
        });
        a.start();
        new Thread(() -> {
            System.out.println("进入线程b");
            LockSupport.unpark(a);
            System.out.println("唤醒线程a");
        }).start();
    }
}
