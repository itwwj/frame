package com.gitee.spring.interceptor.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jie
 */

@RestController
@AllArgsConstructor
public class InterceptorController {


    @GetMapping("/test01")
    public String test1(){
        return "通过";
    }


}
