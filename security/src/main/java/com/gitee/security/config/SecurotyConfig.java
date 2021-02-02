package com.gitee.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author jie
 */
@EnableWebSecurity
public class SecurotyConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // super.configure(http);
        //定制请求的授权规则

        //放行所有访问首页的请求
        http.authorizeRequests().antMatchers("/").denyAll();
        //开启登录功能
        http.formLogin();
        //开启注销功能
        http.logout();
    }

    /**
     * 认证规则
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // super.configure(auth);
        auth.inMemoryAuthentication().withUser("root").password("root").roles("root");

    }
}
