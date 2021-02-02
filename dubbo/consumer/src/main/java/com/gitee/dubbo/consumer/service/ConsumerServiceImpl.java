package com.gitee.dubbo.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gitee.dubbo.producer.service.ProducerService;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author jie
 */
@Data
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Reference
    private ProducerService service;

    @Override
    public String consumer() {
        return service.producer();
    }
}
