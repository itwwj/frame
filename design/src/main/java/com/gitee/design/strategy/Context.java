package com.gitee.design.strategy;

import lombok.AllArgsConstructor;

/**
 * @author jie
 */
@AllArgsConstructor
public class Context {

    private IStrategy straegy;

    public void operate() {
        straegy.operate();
    }
}
