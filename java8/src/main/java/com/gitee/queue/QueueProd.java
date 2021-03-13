package com.gitee.queue;

import lombok.Data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jie
 */
public class QueueProd {

    public static void main(String[] args) {
        MyResource resource=new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(()->{
            try {
                resource.pro();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                resource.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}


class MyResource {
    private volatile boolean FLAG = true;
    private AtomicInteger integer = new AtomicInteger();

    BlockingQueue<String> queue;

    public MyResource(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void pro() throws InterruptedException {
        String data = "";
        boolean offer;
        while (FLAG) {
            data = integer.incrementAndGet() + "";
            offer = queue.offer(data, 2L, TimeUnit.SECONDS);
            if (offer) {
                System.out.println(Thread.currentThread().getName() + " 插入队列成功 " + data);
            } else {
                System.out.println(Thread.currentThread().getName() + " 插入队列失败 " + data);
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public void consumer() throws InterruptedException {
        String result = "";
        while (FLAG) {
            result = queue.poll(2L, TimeUnit.SECONDS);
            if (null==result|| result.equals("")){
                FLAG=false;
                return;
            }
            System.out.println(Thread.currentThread().getName()+" 消费成功 "+result);
        }
    }
}