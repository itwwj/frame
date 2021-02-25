package com.gitee.security.config;

import com.gitee.security.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * EnableGlobalMethodSecurity
 * securedEnable开启Secured注解支持
 * prePostEnabled开启prePostEnabled注解的支持
 *
 * @author jie
 */
@Data
@Configuration
@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityByDbConfig extends WebSecurityConfigurerAdapter {

    private final UserService service;

    /**
     * 绑定认证数据源
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
    }

    /**
     * 没有这个会报错
     * There is no PasswordEncoder mapped for the id "null"
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义拦截
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登出跳转界面 退出地址
        http.logout().logoutUrl("/logout")
                //登出跳转页面
                .logoutSuccessUrl("/user/logout").permitAll();
        //403界面
        http.exceptionHandling().accessDeniedPage("/unauth.html");
        http.formLogin()
                //登录页面
                .loginPage("/login.html")
                //登录访问路径
                .loginProcessingUrl("/user/login")
                //成功之后跳转路径
                .defaultSuccessUrl("/success.html").permitAll()
                .and().authorizeRequests()
                //哪些路径不需要认证
                .antMatchers("/", "/user/login").permitAll()
                //只有具有admin权限的用户才可以访问
                //.antMatchers("/user/index").hasAnyAuthority("admin")
                .anyRequest().authenticated()
                //设置自动登录
                .and().rememberMe().tokenRepository(tokenRepository())
                //有效时长
                .tokenValiditySeconds(60)
                //查询数据库的service
                .userDetailsService(service)
                //关闭csrf防护
                .and().csrf().disable();
    }
   private final DataSource dataSource;
    /**
     * 配置自动保存token
     * @return
     */
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //项目启动创建表
        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


}
