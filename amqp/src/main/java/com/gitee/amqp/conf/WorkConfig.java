package com.gitee.amqp.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jie
 */
@Configuration
public class WorkConfig {
    public static final String WORK_QUEUE = "work";

    @Bean("work")
    public Queue queue() {
        return new Queue(WORK_QUEUE);
    }
}
