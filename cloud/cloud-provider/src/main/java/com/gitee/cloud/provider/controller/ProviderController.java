package com.gitee.cloud.provider.controller;

import com.gitee.cloud.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jie
 */
@RestController
public class ProviderController {
    @Autowired
    private ProviderService service;

    @GetMapping("/pro")
    public String provider() {
        return service.provider();
    }
}
