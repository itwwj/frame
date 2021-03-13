package com.gitee.design.builder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author jie
 */
public class CarMain {
    public static void main(String[] args) {
        Director director = new Director();
        director.getABenzBuilser().run();
        director.getBBenzBuilser().run();
        director.getCBMWBuilser().run();
        director.getDBMWBuilser().run();

    }
}
