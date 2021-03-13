package com.gitee.design.factory;

/**
 * @author jie
 */
public class Dog implements Animal {
    @Override
    public void say() {
        System.out.println("dog 汪汪");
    }

    @Override
    public void run() {
        System.out.println("dog 跑");
    }
}
