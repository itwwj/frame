package com.gitee.design.singleton;

/**
 * 饿汉式单例
 *
 * @author jie
 */
public class EagerSingleton {

    private static EagerSingleton singleton = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return singleton;
    }
}
