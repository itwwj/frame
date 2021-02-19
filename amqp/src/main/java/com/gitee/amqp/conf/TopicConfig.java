package com.gitee.amqp.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jie
 */
@Configuration
public class TopicConfig {

    public static final String TOPIC_MESSAGE="topic.message";
    public static final String TOPIC_MESSAGES="topic.messagess";
    public static final String TOPIC_EXCHANGE="exchange";

    @Bean(name="message")
    public Queue queueMessage() {
        return new Queue(TOPIC_MESSAGE);
    }

    @Bean(name="messages")
    public Queue queueMessages() {
        return new Queue(TOPIC_MESSAGES);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(TOPIC_MESSAGE);
    }

    @Bean
    Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange) {
        //*表示一个词,#表示零个或多个词
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
