package com.gitee.ThreadPool;

import java.util.concurrent.*;

/**
 * @author jie
 */
public class ThreadPoolUtils {

    /**
     * 创建一个常驻线程为2 最大容纳线程为5 多余线程存活为1 抛弃队列中等待最久的任务的线程池
     * @return
     */
    public static ExecutorService myThreadPool(){

        return new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
    }
}
