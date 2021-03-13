package com.gitee.design.prototype;

import lombok.Data;

/**
 * @author jie
 */
@Data
public class Mail implements Cloneable {
    /**
     * 收件人
     */
    private String receiver;
    /**
     * 邮件名称
     */
    private String subject;
    /**
     * 称谓
     */
    private String application;
    /**
     * 邮件内容
     */
    private String contxt;
    /**
     * 邮件尾部
     */
    private String tail;

    public Mail(AdvTemplate template) {
        contxt = template.getAdvContext();
        subject = template.getAdvSubJect();
    }

    @Override
    protected Mail clone() throws CloneNotSupportedException {
        return (Mail) super.clone();
    }
}
