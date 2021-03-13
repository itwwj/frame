package com.gitee.design.factory;

/**
 * @author jie
 */
public class Cat implements Animal {
    @Override
    public void say() {
        System.out.println("cat 喵喵");
    }

    @Override
    public void run() {
        System.out.println("cat 猫步");
    }
}
