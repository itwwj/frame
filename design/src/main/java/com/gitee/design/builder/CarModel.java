package com.gitee.design.builder;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jie
 */
public abstract class CarModel {
    /**
     * 记录方法的执行顺序
     */
    private ArrayList<String> list = new ArrayList<>();

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

    public void run() {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals("start")) {
                this.start();
            } else if (s.equals("stop")) {
                this.stop();
            } else if (s.equals("alarm")) {
                this.alarm();
            } else if (s.equals("engineBoom")) {
                this.engineBoom();
            }
        }
    }


    public final void setSequence(ArrayList arrayList) {
        this.list = arrayList;
    }
}
