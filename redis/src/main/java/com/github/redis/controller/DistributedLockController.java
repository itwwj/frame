package com.github.redis.controller;

import com.github.redis.locks.DistributedLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jie
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class DistributedLockController {

    private final DistributedLock distributedLock;
    private static Integer testint = 20;
    private static final String KEY = "test";

    @RequestMapping("distributedLock")
    public void distributeLock() {
        try {
            boolean test = distributedLock.lock(KEY);
            if (test && testint > 0) {
                testint = testint - 1;
                log.info("testint          :" + testint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            distributedLock.releaseLock(KEY);
        }
    }

    @RequestMapping("test")
    public void testLock() {
        if (testint > 0) {
            testint = testint - 1;
            log.info("testint          :" + testint);
        }
    }
}
