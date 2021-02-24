package com.gitee.security.controller;

import com.gitee.security.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jie
 */

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("findOne")
    public User findOne() {
        return new User();
    }
}
