package com.gitee.dubbo.producer.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author jie
 */
@Service
@Component
public class ProducerServiceImpl implements ProducerService {

    @Override
    public String producer() {
        return "axiba";
    }
}
