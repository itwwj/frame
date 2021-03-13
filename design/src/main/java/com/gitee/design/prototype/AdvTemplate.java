package com.gitee.design.prototype;

import lombok.Data;

/**
 * @author jie
 */
@Data
public class AdvTemplate {
    /**
     * 广告信名称
     */
    private String advSubJect;
    /**
     * 广告新内容
     */
    private String advContext;

    public AdvTemplate() {
        advSubJect = "xxx银行国庆信用卡抽奖活动";
        advContext = "国庆抽奖活动通知： 只要刷卡就送你一百万！ ...";
    }
}
