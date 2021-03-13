package com.gitee.design.abstractFactory;

/**
 * @author jie
 */
public abstract class AbstractYellowHuman implements Human{
    @Override
    public void getColor() {
        System.out.println("Yellow Human color is Yellow");
    }

    @Override
    public void talk() {
        System.out.println("Yellow Human talk is chinses");
    }
}
