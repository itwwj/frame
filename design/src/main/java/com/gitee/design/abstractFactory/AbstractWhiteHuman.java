package com.gitee.design.abstractFactory;

/**
 * @author jie
 */
public abstract class AbstractWhiteHuman implements Human{
    @Override
    public void getColor() {
        System.out.println("white Human color is white");
    }

    @Override
    public void talk() {
        System.out.println("white Human talk is english");
    }
}
