package com.gitee.spring.interceptor.aop;

import org.springframework.stereotype.Component;

/**
 * @author jie
 */
@Component
public class MathCalculator {

    public int division(int i, int j) {
        return i / j;
    }
}
