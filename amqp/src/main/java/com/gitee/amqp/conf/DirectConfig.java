package com.gitee.amqp.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct模式rabbitmqz配置
 *
 * 此模式不需要交换机  点对点消息模型
 * @author jie
 */

@Configuration
public class DirectConfig {
    public static final String DIRECT_QUEUE = "queue";

    @Bean
    public Queue queue() {
        return new Queue(DIRECT_QUEUE);
    }
}
