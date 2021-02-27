package com.gitee.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 生成token
 * @author jie
 */
@Component
public class TokenManager {
    /**
     * token有效时长
     */
    private long tokenEcpiration = 24*60*60*1000;
    /**
     * 编码秘钥
     */
    private String tokenSignKey = "gitee";

    /**
     * 使用jwt根据用户名生成token
     * @param username
     * @return
     */
    public String createToken(String username) {
        String token = Jwts.builder().setSubject(username)
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis()+tokenEcpiration))
                //加密和编码方式
                .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    /**
     * 根据token字符串得到用户信息
     * @param token
     * @return
     */
    public String getUserInfoFromToken(String token) {
        return Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
    }

}
