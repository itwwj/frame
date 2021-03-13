package com.gitee.design.responsibility.fuge;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jie
 */
public abstract class AuthLink {
    /**
     * 时间格式化
     */
    protected SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     *  级别⼈人员ID
     */
    protected String levelUserId;
    /**
     * 级别⼈人员姓名
     */
    protected String levelUserName;
    /**
     * 责任链
     */
    private AuthLink next;

    public AuthLink(String levelUserId, String levelUserName) {
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
    }
    public AuthLink next() {
        return next;
    }
    public AuthLink appendNext(AuthLink next) {
        this.next = next;
        return this;
    }

    /**
     * 请求处理抽象
     * @param uId
     * @param orderId
     * @param authDate
     * @return
     */
    public abstract AuthInfo doAuth(String uId, String orderId, Date
            authDate);
}
