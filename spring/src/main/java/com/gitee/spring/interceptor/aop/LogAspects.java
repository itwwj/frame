package com.gitee.spring.interceptor.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author jie
 */
@Aspect
@Component
public class LogAspects {


    @Pointcut("execution(public int com.gitee.spring.interceptor.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    /**
     * joinPoint必须放在参数列表的第一位，否则spring无法识别
     *
     * @param joinPoint
     */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        System.out.println("方法开始运行  参数列表：{" + Arrays.asList(args).toString() + "}");
    }

    @After("pointCut()")
    public void logEnd() {
        System.out.println("方法结束运行");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(Object result) {
        System.out.println("方法返回数据  数据列表：{" + result.toString() + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "error")
    public void logException(Exception error) {
        System.out.println("方法出现异常  异常信息：{" + error.toString() + "}");
    }

}
