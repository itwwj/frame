package com.gitee.design.template;

/**
 * @author jie
 */
public class HummerH1Model extends HummerModel {
    @Override
    public void start() {
        System.out.println("悍马H1发动。。。");
    }

    @Override
    public void stop() {
        System.out.println("悍马H1停止。。。");
    }

    @Override
    public void alarm() {
        System.out.println("悍马H1鸣笛。。。");
    }

    @Override
    public void engineBoom() {
        System.out.println("悍马H1引擎 轰鸣。。。");
    }
}
