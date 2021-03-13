package com.gitee.design.mediation;

/**
 * 抽象中介者
 * @author jie
 */
public abstract class AbstractMediator {
    protected Purchase purchase;
    protected Sale sale;
    protected Stock stock;


    public AbstractMediator(){
        purchase = new Purchase(this);
        sale = new Sale(this);
        stock = new Stock(this);
    }

    /**
     * 处理多个对象之间的关系
     * @param str
     * @param objects
     */
    public abstract void execute(String str,Object...objects);
}
