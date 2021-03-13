package com.gitee.design.responsibility;

import lombok.AllArgsConstructor;

/**
 * @author jie
 */
public class Women implements IWomen {

    /**
     * 状态
     * 1--未出嫁
     * 2--出嫁
     * 3--夫死
     */
    private int type = 0;
    /**
     * 请示
     */
    private String request = "";

    public Women(int _type,String _request){
        this.type = _type;
        //为了便于显示， 在这里做了点处理
        switch(this.type){
            case 1:
                this.request = "女儿的请求是： " + _request;
                break;
            case 2:
                this.request = "妻子的请求是： " + _request;
                break;
            case 3:
                this.request = "母亲的请求是： " + _request;
        }
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getRequest() {
        return request;
    }
}
