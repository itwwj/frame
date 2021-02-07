package com.gitee.frame.bootmybatis.entity;

import lombok.Data;

/**
 * @author jie
 */

public enum StatusEnum {
    CONNECT(200, "在线"),
    DISCONNECT(500, "离线");


    private Integer status;
    private String msg;

    private StatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static StatusEnum getStatus(Integer status) {
        if (status == 200) {
            return CONNECT;
        } else {
            return DISCONNECT;
        }
    }
}
