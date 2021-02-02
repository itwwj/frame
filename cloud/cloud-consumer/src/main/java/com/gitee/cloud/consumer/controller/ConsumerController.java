package com.gitee.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author jie
 */
@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate template;

    @GetMapping("/con")
    public String consumer() {
        return template.getForObject("http://PROVIDER-cloud/ticket", String.class);
    }
}
