package com.gitee.spring.interceptor.aop.config;

import com.gitee.spring.interceptor.aop.LogAspects;
import com.gitee.spring.interceptor.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * aop底层为动态代理 能够在程序运行时动态地将代码切入到指定方法指定位置进行运行的编程方式
 *
 * EnableAspectJAutoProxy开启主接班的aop功能
 * @author jie
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {


}
