package com.gitee.jwt.utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jie
 */
@RestController
@RequestMapping("/auth")
public class JwtController {

    @GetMapping("/jwt/{username}")
    public String jwtAuth(@PathVariable("username") String username) {
        return JwtUtil.sign(username);
    }

}
