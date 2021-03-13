package com.gitee.design.builder;

import java.util.ArrayList;

/**
 * @author jie
 */
public abstract class CarBuilder {
    /**
     * 组装顺序
     * @param arrayList
     */
    public abstract void setSqquence(ArrayList<String> arrayList);

    /**
     * 设计模型
     * @return
     */
    public abstract CarModel getCarModel();
}
