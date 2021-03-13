package com.gitee.amqp.conf;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct模式rabbitmqz配置
 * <p>
 * 路由模式
 *
 * @author jie
 */

@Configuration
public class DirectConfig {
    public static final String DIRECT_QUEUE_A = "queueA";
    public static final String DIRECT_QUEUE_B = "queueA";
    public static final String DIRECT_QUEUE_C = "queueA";
    public static final String DIRECT_EXCHANGE = "direct";
    public static final String ROUTINGKEY_A = "A";
    public static final String ROUTINGKEY_B = "B";
    public static final String ROUTINGKEY_C = "C";

    @Bean("directQueueA")
    public Queue queueA() {
        return new Queue(DIRECT_QUEUE_A);
    }
    @Bean("directQueueB")
    public Queue queueB() {
        return new Queue(DIRECT_QUEUE_B);
    }
    @Bean("directQueueC")
    public Queue queueC() {
        return new Queue(DIRECT_QUEUE_C);
    }


    @Bean("direct")
    public DirectExchange direct() {
        return ExchangeBuilder.directExchange(DIRECT_EXCHANGE).build();
    }

    @Bean
    public Binding bindingA(@Qualifier("directQueueA") Queue queue, @Qualifier("direct")DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_A);
    }
    @Bean
    public Binding bindingB(@Qualifier("directQueueB") Queue queue,@Qualifier("direct") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_B);
    }
    @Bean
    public Binding bindingC(@Qualifier("directQueueC") Queue queue, @Qualifier("direct")DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_C);
    }
}
