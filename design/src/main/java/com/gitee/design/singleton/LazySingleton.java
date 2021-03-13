package com.gitee.design.singleton;

/**
 * 懒汉式单例
 *
 * @author jie
 */
public class LazySingleton {

    private static LazySingleton singleton = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (singleton == null) {
            synchronized (singleton) {
                if (singleton == null) {
                    singleton = new LazySingleton();
                }
            }
        }
        return singleton;
    }
}
