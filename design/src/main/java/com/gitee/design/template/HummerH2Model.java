package com.gitee.design.template;

/**
 * @author jie
 */
public class HummerH2Model extends HummerModel {
    @Override
    public void start() {
        System.out.println("悍马H2发动。。。");
    }

    @Override
    public void stop() {
        System.out.println("悍马H2停止。。。");
    }

    @Override
    public void alarm() {
        System.out.println("悍马H2鸣笛。。。");
    }

    @Override
    public void engineBoom() {
        System.out.println("悍马H2引擎 轰鸣。。。");
    }
}
