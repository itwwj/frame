package com.gitee.spring.interceptor.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author jie
 */
@Component
public class Cat {



    @PostConstruct
    public void init(){
        System.out.println("Cat类PostConstruct注解");
    }
    @PreDestroy
    public void detory(){
        System.out.println("Cat类PreDestroy注解");
    }
}
