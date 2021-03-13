package com.gitee.design.responsibility;

import lombok.Setter;

/**
 * @author jie
 */
public abstract class Handler {

    public final static int FATHER_LEVEL_REQUEST = 1;
    public final static int HUSBAND_LEVEL_REQUEST = 2;
    public final static int SON_LEVEL_REQUEST = 3;
    @Setter
    private int level = 0;
    @Setter
    private Handler nextHandler;

    public Handler(int level) {
        this.level = level;
    }

    public final void HandlerMessage(IWomen women) {
        if (women.getType() == this.level) {
            this.response(women);
        } else {
            if (this.nextHandler != null) {
                nextHandler.HandlerMessage(women);
            } else {
                System.out.println("链路终点，按没有人同意处理");
            }
        }
    }
    /**
     * 处理抽象方法
     *
     * @param women
     */
    protected abstract void response(IWomen women);


}
