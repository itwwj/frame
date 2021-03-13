package com.gitee.design.factory;

/**
 * 抽象出工厂
 * @author jie
 */
public abstract class AbstractAnimalFactory {

    public abstract <T extends Animal> T createAnimal(Class<T> tClass);
}
