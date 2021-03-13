package com.gitee.design.proxy;

import lombok.AllArgsConstructor;

/**
 * @author jie
 */
@AllArgsConstructor
public class GamePlayerProxy implements IGamePlayer {
    IGamePlayer gamePlayer;

    @Override
    public void login(String username, String password) {
        gamePlayer.login(username, password);
    }

    @Override
    public void killBoss() {
        gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        gamePlayer.upgrade();
    }
}
