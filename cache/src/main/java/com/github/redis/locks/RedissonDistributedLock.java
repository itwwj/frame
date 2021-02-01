package com.github.redis.locks;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * 基于redisson的分布式锁
 *
 * @author jie
 */
@Data
@Slf4j
@RequiredArgsConstructor
public class RedissonDistributedLock implements DistributedLock {

    private final RedissonClient redissonClient;

    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        try {
            RLock lock = redissonClient.getLock(key);
            lock.tryLock(expire, retryTimes, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("获取锁失败" + e.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean releaseLock(String key) {
        try {
            RLock lock = redissonClient.getLock(key);
            lock.unlock();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
