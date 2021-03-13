package com.gitee.design.command;

/**
 * 抽象组
 *
 * @author jie
 */
public abstract class Group {
    /**
     * 寻找分组
     */
    public abstract void find();

    /**
     * 增加功能
     */
    public abstract void add();

    /**
     * 删除功能
     */
    public abstract void delete();

    /**
     * 修改功能
     */
    public abstract void change();

    /**
     * 变更计划
     */
    public abstract void plan();
}
