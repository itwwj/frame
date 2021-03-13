package com.gitee.design.builder;

import java.util.ArrayList;

/**
 * @author jie
 */
public class BenzBuilder extends CarBuilder {

    private BenzModel benz = new BenzModel();

    @Override
    public void setSqquence(ArrayList<String> arrayList) {
        this.benz.setSequence(arrayList);
    }

    @Override
    public CarModel getCarModel() {
        return benz;
    }
}
