package com.github.cache.controller;

import com.github.cache.entity.TestEntity;
import com.github.cache.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jie
 */
@AllArgsConstructor
@RestController
public class TestController {
    private final TestService service;

    @GetMapping("/findId")
    public TestEntity findById(Long id) {
        return service.findById(id);
    }
}
