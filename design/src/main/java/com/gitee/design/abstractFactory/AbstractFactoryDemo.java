package com.gitee.design.abstractFactory;

/**
 * @author jie
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {

        FemaleFactory femaleFactory = new FemaleFactory();
        MaleFactory maleFactory = new MaleFactory();

        Human blackHuman = femaleFactory.createBlackHuman();
        Human yellowHuman = maleFactory.createYellowHuman();

        blackHuman.getColor();
        blackHuman.talk();
        blackHuman.getSex();

        yellowHuman.getColor();
        yellowHuman.talk();
        yellowHuman.getSex();
    }
}
