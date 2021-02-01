package com.github.redis.utils;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * jackson 自定义序列化 & 反序列化 规则
 *
 * @author jie
 */
public class JacksonModule extends SimpleModule {

    public JacksonModule() {
        super(PackageVersion.VERSION);
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.addSerializer(BigInteger.class, ToStringSerializer.instance);
        this.addSerializer(BigDecimal.class, ToStringSerializer.instance);
    }
}
