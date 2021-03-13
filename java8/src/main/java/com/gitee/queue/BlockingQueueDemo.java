package com.gitee.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ArrayBlockingQueue :由数组结构组成的有界限塞队列。        (重点)
 * LinkedBlockingQuege : 由链表结构组成的有界(但大小默认值为Integer.MAX_VALUE)阻塞队列。       (重点)
 * PriorityBlockingQueue:支持优先级排序的无界祖塞队列。
 * DelayQueue:使用优先级队列实现的延迟无界阻塞队列。
 * SynchronousQueue:不存储元素的阻塞队列,也即单个元素的队列。生产一个消费一个 只有一个元素        (重点)
 * LinkedTransferQueue:由链表结构组成的无界阻塞队列。
 * LinkedBlockingDeque: 由链表结构组成的双向阻塞队列
 *
 * @author jie
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.put(i + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
