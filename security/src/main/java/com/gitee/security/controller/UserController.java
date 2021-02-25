package com.gitee.security.controller;

import com.gitee.security.entity.User;
import com.gitee.security.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jie
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService service;

    @GetMapping("findOne")
    public User findOne() {
        return new User();
    }


    /**
     * Secured注解 判断是否具有角色，另外需要注意的是这里匹配的字符串需要添加前缀"ROLE_"
     * <p>
     * 使用注解先要开启注解功能！
     * EnableGlobalMethodSecurity(securedEnabled=true)
     *
     * @return
     */
    @ResponseBody
    @GetMapping("secured")
    @Secured({"ROLE_root", "ROLE_admin"})
    public String secured() {
        return "secured";
    }

    /**
     * preAuthorize注解 在进入方法前权限验证
     * <p>
     * 使用注解先要开启注解功能！
     * EnableGlobalMethodSecurity(prePostEnabled=true)
     * <p>
     * hasAuthority 如果当前的主体具有指定的权限，则返回 true,否则返回 false
     * hasAnyAuthority  如果当前的主体有任何提供的角色（给定的作为一个逗号分隔的字符串列表）的话，返回true
     * hasRole 如果用户具备给定角色就允许访问，否则出现403 如果当前主题具有指定角色，则返回true
     * hasAnyRole 表示用户具备任何一个条件都可以访问
     *
     * @return
     */
    @ResponseBody
    @GetMapping("preAuthorize")
    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    public String preAuthorize() {
        return "preAuthorize";
    }


    /**
     * postAuthorize 方法执行后在进行权限验证
     * 使用注解先要开启注解功能！
     * EnableGlobalMethodSecurity(prePostEnabled=true)
     *
     * @return
     */
    @ResponseBody
    @GetMapping("postAuthorize")
    @PostAuthorize("hasAnyAuthority('ROLE_admin')")
    public String postAuthorize() {
        return "postAuthorize";
    }
}
