package com.gitee.design.proxy;

/**
 * @author jie
 */
public class ProxyMain {
    public static void main(String[] args) {

        IGamePlayer player=new GamePlayer("axiba");
        player.login("axiba","123");
        player.killBoss();
        player.upgrade();

        IGamePlayer gamePlayer=new GamePlayerProxy(player);
        gamePlayer.login("axiba","123");
        gamePlayer.killBoss();
        gamePlayer.upgrade();
    }
}
