package com.gitee.design.proxy;

import lombok.AllArgsConstructor;

/**
 * @author jie
 */
@AllArgsConstructor
public class GamePlayer implements IGamePlayer {
    private String name;
    @Override
    public void login(String username, String password) {
        System.out.println("用户："+name +" 登录游戏");
    }

    @Override
    public void killBoss() {
        System.out.println(name+" 在打怪");
    }

    @Override
    public void upgrade() {
        System.out.println(name+" 升级了");
    }
}
