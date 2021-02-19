package com.gitee.amqp.sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.gitee.amqp.conf.DirectConfig.DIRECT_QUEUE;
import static com.gitee.amqp.conf.FanoutConfig.*;
import static com.gitee.amqp.conf.TopicConfig.TOPIC_MESSAGE;
import static com.gitee.amqp.conf.TopicConfig.TOPIC_MESSAGES;

/**
 * @author jie
 */
@Slf4j
@Component
public class SubListener {


    @RabbitListener(queues = DIRECT_QUEUE)
    public void direct(String msg) {
        log.info(msg);
    }

    /**
     * //监听器监听指定的Queue
     *
     * @param str
     */
    @RabbitListener(queues = TOPIC_MESSAGE)
    public void topic1(String str) {
        log.info(str);
    }

    /**
     * //监听器监听指定的Queue
     *
     * @param str
     */
    @RabbitListener(queues = TOPIC_MESSAGES)
    public void topic2(String str) {
        log.info(str);
    }

    @RabbitListener(queues = TOPIC_MESSAGES)
    public void topic3(String str){
        log.info(str);
    }

    @RabbitListener(queues = FANOUT_A)
    public void fanoutA(String str) {
        log.info(str);
    }

    @RabbitListener(queues = FANOUT_B)
    public void fanoutB(String str) {
        log.info(str);
    }

    @RabbitListener(queues = FANOUT_C)
    public void fanoutC(String str) {
        log.info(str);
    }

}
