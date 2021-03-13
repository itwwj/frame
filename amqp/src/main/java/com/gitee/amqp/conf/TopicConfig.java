package com.gitee.amqp.conf;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通配符订阅 动态路由模式
 *
 * @author jie
 */
@Configuration
public class TopicConfig {

    public static final String TOPIC_MESSAGE = "topic.message";
    public static final String TOPIC_MESSAGES = "topic.messagess";
    public static final String TOPIC_EXCHANGE = "exchange";
    public static final String ROUTING_TOPIC_A = "topic.*";
    public static final String ROUTING_TOPIC_B = "axiba.*";

    @Bean(name = "message")
    public Queue queueMessage() {
        return new Queue(TOPIC_MESSAGE);
    }

    @Bean(name = "messages")
    public Queue queueMessages() {
        return new Queue(TOPIC_MESSAGES);
    }

    @Bean("topics")
    public TopicExchange exchange() {
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE).durable(true).build();
    }

    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, @Qualifier("topics") TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(ROUTING_TOPIC_B);
    }

    @Bean
    Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, @Qualifier("topics") TopicExchange exchange) {
        //*表示一个词,#表示零个或多个词
        return BindingBuilder.bind(queueMessages).to(exchange).with(ROUTING_TOPIC_A);
    }
}
