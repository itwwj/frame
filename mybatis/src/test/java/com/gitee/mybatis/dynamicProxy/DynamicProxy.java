package com.gitee.mybatis.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理简单实现
 *
 * @author jie
 */
public class DynamicProxy {
    public static void main(String[] args) {
        Animal cat = new Cat();
        CatPlus catPlus = new CatPlus(cat);
        Animal catplus = (Animal) catPlus.proxyInsance();
        catplus.run();
        catplus.say();
    }

}

interface Animal {
    void run();

    void say();
}

class Cat implements Animal {

    @Override
    public void run() {
        System.out.println("跳");
    }

    @Override
    public void say() {
        System.out.println("叫");
    }
}

class CatPlus implements InvocationHandler {
    private Animal animal;

    public CatPlus(Animal animal) {
        this.animal = animal;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(animal, args);
        after();
        return invoke;
    }

    private void after() {
        System.out.println("动作完成");
    }

    private void before() {
        System.out.println("小猫准备");

    }

    public Object proxyInsance() {
        return Proxy.newProxyInstance(animal.getClass().getClassLoader(), animal.getClass().getInterfaces(), this);
    }

}