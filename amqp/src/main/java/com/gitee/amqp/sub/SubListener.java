package com.gitee.amqp.sub;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.gitee.amqp.conf.DirectConfig.*;
import static com.gitee.amqp.conf.FanoutConfig.*;
import static com.gitee.amqp.conf.TopicConfig.TOPIC_MESSAGE;
import static com.gitee.amqp.conf.TopicConfig.TOPIC_MESSAGES;
import static com.gitee.amqp.conf.WorkConfig.WORK_QUEUE;

/**
 * @author jie
 */
@Slf4j
@Component
public class SubListener {

    /**
     * 路由模式
     *
     * @param msg
     */
    @RabbitListener(queues = DIRECT_QUEUE_A)
    public void directA(String msg) {
        log.info(msg);
    }

    @RabbitListener(queues = DIRECT_QUEUE_B)
    public void directB(String msg) {
        log.info(msg);
    }

    @RabbitListener(queues = DIRECT_QUEUE_C)
    public void directC(String msg) {
        log.info(msg);
    }

    /**
     * 动态路由模式
     *
     * @param str
     */
    @RabbitListener(queues = TOPIC_MESSAGE)
    public void topic1(String str) {
        log.info(str);
    }

    @RabbitListener(queues = TOPIC_MESSAGES)
    public void topic2(String str) {
        log.info(str);
    }

    /**
     * 广播模式
     *
     * @param str
     */
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


    /**
     * work模式
     *
     * @param str
     */
    @RabbitListener(queues = WORK_QUEUE)
    public void workA(String str) {
        log.info(str);
    }

    @RabbitListener(queues = WORK_QUEUE)
    public void worB(String str) {
        log.info(str);
    }

    //==================================================注解方式创建交换机和队列================================================//

    @RabbitListener(queuesToDeclare = {
            @Queue("work22")
    })
    public void workC(Message message, Channel channel) throws IOException {
        try {
            log.info(new String(message.getBody()));
            //手动确认消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            //如果没有手动签收就将消息重新放到queue broker会重新把消息发到客户端
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
            //消息丢弃
            //channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }


    /**
     * 创建临时队列并创建和绑定logs交换机
     *
     * @param str
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs", type = "fanout")
            )
    })
    public void fanoutD(String str) {
        log.info(str);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs", type = "fanout")
            )
    })
    public void fanoutE(String str) {
        log.info(str);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "direct1"),
                    key = {"A", "B"}
            )
    })
    public void directD(String str) {
        log.info(str);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "direct1"),
                    key = {"C", "D"}
            )
    })
    public void directE(String str) {
        log.info(str);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topic", type = "topic"),
                    key = {"axiba.*", "aaa.#"}
            )
    })
    public void topic3(String str) {
        log.info(str);
    }


    @RabbitListener()
    public void dead() {

    }
}
