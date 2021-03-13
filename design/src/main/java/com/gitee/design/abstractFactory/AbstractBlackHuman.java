package com.gitee.design.abstractFactory;

/**
 * @author jie
 */
public abstract class AbstractBlackHuman implements Human{
    @Override
    public void getColor() {
        System.out.println("Black Human color is Black");
    }

    @Override
    public void talk() {
        System.out.println("Black Human talk is 听不懂");
    }
}
