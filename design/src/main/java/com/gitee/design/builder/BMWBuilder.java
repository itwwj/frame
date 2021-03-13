package com.gitee.design.builder;

import java.util.ArrayList;

/**
 * @author jie
 */
public class BMWBuilder extends CarBuilder {

    private BMWModel benz = new BMWModel();

    @Override
    public void setSqquence(ArrayList<String> arrayList) {
        this.benz.setSequence(arrayList);
    }

    @Override
    public CarModel getCarModel() {
        return benz;
    }
}
