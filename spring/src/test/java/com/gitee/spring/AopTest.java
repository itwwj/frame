package com.gitee.spring;

import com.gitee.spring.interceptor.aop.MathCalculator;
import com.gitee.spring.interceptor.aop.config.AopConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jie
 */
public class AopTest {

    @Test
    public void aop(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        MathCalculator bean = context.getBean(MathCalculator.class);
        int div = bean.division(4, 2);
        context.close();
    }
}
