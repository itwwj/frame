package com.github.jpa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * TODO
 * EnableSpringDataWebSupport 启用springmvc对spring data的支持
 * EnableWebMvc 启用spring mvc
 * @author jie
 * @date 2020/12/6 21:53
 */
@Configuration
@ComponentScan(basePackages = {"com.github.mongo.controller"})
@EnableWebMvc
@EnableSpringDataWebSupport
public class WebMvcConfig extends WebMvcConfigurerAdapter {

}
