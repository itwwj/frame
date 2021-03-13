package com.gitee.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池优点：
 * 第一:降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
 * 第二:提高响应速度。当任务到达时,任务可以不需要的等到线程创建就能立即执行。
 * 第三:提高线程的可管理性。线程是稀缺资源,如果无限制的创建,不仅会消耗系统资源,还会降低系统的稳定性,
 * 使用线程池可以进行统一的分配,调优和监控1
 * <p>
 * <p>
 * <p>
 * 线程池创建方式：
 * newFixedThreadPool创建固定线程数线程池
 * newSingleThreadExecutor 创建一个线程的线程池
 * newCachedThreadPool  创建n个线程的线程池 随机扩容
 * <p>
 * <p>
 * <p>
 * <p>
 * 线程池七大参数：
 * public ThreadPoolExecutor(int corePoolSize,
 * int maximumPoolSize,
 * long keepAliveTime,
 * TimeUnit unit,
 * BlockingQueue<Runnable> workQueue,
 * ThreadFactory threadFactory,
 * RejectedExecutionHandler handler)
 *
 *
 *
 * corePoolSize：线程池中的常驻核心线程数
 * maximumPoolSize：线程池能够容纳的最大线程数，此值必须大于1
 * keepAliveTime：多余的空闲线程的存活时间。
 * unit：keepAliveTime的单位。
 * workQueue：任务队列，被提交但尚未被执行的任务
 * threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程一般默认即可
 * handler：拒绝策略，表示当队列满了并且工作线程大于等于线程池的最大线程数
 *
 * @author jie
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {

    }

    public static void newFixedThreadPool() {
        ExecutorService service = ThreadPoolUtils.myThreadPool();

    }
}
