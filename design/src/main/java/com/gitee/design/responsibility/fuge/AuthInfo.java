package com.gitee.design.responsibility.fuge;

import lombok.Data;

/**
 * @author jie
 */
@Data
public class AuthInfo {


    private String code;
    private String info = "";

    public AuthInfo(String code, String... infos) {
        this.code = code;
        for (String str : infos) {
            this.info = this.info.concat(str);
        }
    }
}
