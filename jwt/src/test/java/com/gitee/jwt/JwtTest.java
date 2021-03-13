package com.gitee.jwt;

import com.gitee.jwt.utils.JwtUtil;
import org.junit.jupiter.api.Test;

/**
 * @author jie
 */
public class JwtTest {
    @Test
    public void create(){
        System.out.println(JwtUtil.sign("root"));
    }
}
