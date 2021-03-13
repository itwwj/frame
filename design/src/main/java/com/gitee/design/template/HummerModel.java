package com.gitee.design.template;

/**
 * @author jie
 */
public abstract class HummerModel {
    /**
     * 车必须要发动
     */
    public abstract void start();

    /**
     * 车必须要能停止
     */
    public abstract void stop();

    /**
     * 车的喇叭会响
     */
    public abstract void alarm();

    /**
     * 引擎会响
     */
    public abstract void engineBoom();

    /**
     * 车会跑
     */
    public  void run(){
        this.start();
        this.engineBoom();
        this.alarm();
        this.stop();
    }
}
