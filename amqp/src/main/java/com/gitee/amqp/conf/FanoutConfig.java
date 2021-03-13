package com.gitee.amqp.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 广播消息
 * @author jie
 */
@Configuration
public class FanoutConfig {
    public static final String FANOUT_A="fanout.A";
    public static final String FANOUT_B="fanout.B";
    public static final String FANOUT_C="fanout.C";
    public static final String FANOUT_EXCHANGE="fanoutExchange";


    @Bean(name="Amessage")
    public Queue AMessage() {
        return new Queue(FANOUT_A);
    }


    @Bean(name="Bmessage")
    public Queue BMessage() {
        return new Queue(FANOUT_B);
    }

    @Bean(name="Cmessage")
    public Queue CMessage() {
        return new Queue(FANOUT_C);
    }

    /**
     * //配置广播路由器
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeA(@Qualifier("Amessage") Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(@Qualifier("Bmessage") Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(@Qualifier("Cmessage") Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

}
