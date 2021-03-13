package com.gitee.design.builder;

import java.util.ArrayList;

/**
 * @author jie
 */
public class Director {
    private ArrayList<String> sequence=new ArrayList<>();
    private BenzBuilder benzBuilder=new BenzBuilder();
    private BMWBuilder bmwBuilder=new BMWBuilder();

    /**
     * 建造一个先启动后停止的奔驰车
     * @return
     */
    public BenzModel getABenzBuilser(){
        this.sequence.clear();
        this.sequence.add("start");
        this.sequence.add("stop");
        this.benzBuilder.setSqquence(sequence);
        return (BenzModel)benzBuilder.getCarModel();
    }

    /**
     * 建造一个先引擎再启动后停止的奔驰车
     * @return
     */
    public BenzModel getBBenzBuilser(){
        this.sequence.clear();
        this.sequence.add("engineBoom");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.benzBuilder.setSqquence(sequence);
        return (BenzModel)benzBuilder.getCarModel();
    }

    /**
     * 建造一个先引擎再启动后停止的宝马车
     * @return
     */
    public BMWModel getCBMWBuilser(){
        this.sequence.clear();
        this.sequence.add("engineBoom");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.bmwBuilder.setSqquence(sequence);
        return (BMWModel)bmwBuilder.getCarModel();
    }
    /**
     * 建造一个只会跑的宝马车
     * @return
     */
    public BMWModel getDBMWBuilser(){
        this.sequence.clear();
        this.sequence.add("start");
        this.bmwBuilder.setSqquence(sequence);
        return (BMWModel)bmwBuilder.getCarModel();
    }
}
