package com.github.redis.locks;

/**
 * 基于redisson的分布式锁
 * @author jie
 */
public class RedissonDistributedLock implements DistributedLock{

    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        return false;
    }

    @Override
    public boolean releaseLock(String key) {
        return false;
    }
}
