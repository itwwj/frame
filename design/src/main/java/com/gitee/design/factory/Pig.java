package com.gitee.design.factory;

/**
 * @author jie
 */
public class Pig implements Animal {
    @Override
    public void say() {
        System.out.println("pig 吼吼");
    }

    @Override
    public void run() {
        System.out.println("pig 走");
    }
}
