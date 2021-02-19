package com.gitee.amqp.pub;

import com.gitee.amqp.conf.DirectConfig;
import com.gitee.amqp.conf.FanoutConfig;
import com.gitee.amqp.conf.TopicConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jie
 */
@Service
public class PubService {

    @Autowired
    private RabbitTemplate template;

    public void directSend(String str){
        template.convertAndSend(DirectConfig.DIRECT_QUEUE,str);
    }

    public void topicSend(String str){
        template.convertAndSend(TopicConfig.TOPIC_EXCHANGE,TopicConfig.TOPIC_MESSAGES,str);
    }

    public void fanoutSend(String str){
        template.convertAndSend(FanoutConfig.FANOUT_EXCHANGE,"",str);
    }
}
