package com.gitee.dubbo.consumer.controller;

import com.gitee.dubbo.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jie
 */
@RestController
public class DubboController {
    @Autowired
    private ConsumerService service;

    @GetMapping("/get")
    public String test() {
        return service.consumer();
    }
}
