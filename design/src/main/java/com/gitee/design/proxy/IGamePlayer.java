package com.gitee.design.proxy;

/**
 * @author jie
 */
public interface IGamePlayer {
    /**
     * 登录游戏
     *
     * @param username
     * @param password
     */
    void login(String username, String password);

    /**
     * 打怪
     */
    void killBoss();

    /**
     * 升级
     */
    void upgrade();
}
