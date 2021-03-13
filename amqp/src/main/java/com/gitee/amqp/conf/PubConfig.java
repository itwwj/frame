package com.gitee.amqp.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * 消息投递回调
 * @author jie
 */
@Slf4j
@Component
public class PubConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void setConfirmAndReturnCallBack() {
        //设置ConfirmCallback
        rabbitTemplate.setConfirmCallback(confirmCallback());
        //设置ReturnCallback
        rabbitTemplate.setReturnCallback(returnCallback());
    }


    /**
     * ReturnCallback接口用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调
     *
     * message   消息对象
     * replyCode   回复的状态码
     * replyText   错误信息
     * exchange     这个消息发送给哪个交换机
     * routingKey    这个消息用的哪个路由键
     */
    RabbitTemplate.ReturnCallback returnCallback() {
        return (message, replyCode, replyText, exchange, routingKey) -> {
            //业务逻辑
            log.info("returnCallback---->message---{},-->replyCode---{},-->replyText---{},-->exchange---{},-->routingKey---{}", message, replyCode, replyText, exchange, routingKey);
        };
    }

    /**
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调
     *
     * correlationData 消息的唯一id,给每一条信息添加一个dataId，放在CorrelationData，这样在RabbitConfirmCallback返回失败的时候可以知道是哪个消息失败
     * ack 消息是否成功收到 true 收到 false 未收到
     * cause  失败原因
     */
    RabbitTemplate.ConfirmCallback confirmCallback() {
        return (correlationData, ack, cause) -> {
            //业务逻辑
            log.info("confirmCallback---->correlationData---{},-------->ack---{},-------->cause---{}", correlationData, ack, cause);
        };
    }
}
