package com.gitee.amqp;

import com.gitee.amqp.pub.PubService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.gitee.amqp.conf.DirectConfig.*;
import static com.gitee.amqp.conf.WorkConfig.WORK_QUEUE;

/**
 * @author jie
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AmqpTest {

    @Autowired
    private PubService service;

    @Autowired
    private RabbitTemplate template;

    @Test
    public void pub() {
        service.directSend("axiba");
    }

    @Test
    public void pub1() {
        service.fanoutSend("fanout");
    }

    @Test
    public void pub3() {
        for (int i = 0; i < 20; i++) {
            service.topicSend("topic" + i);
        }
    }


    @Test
    public void direct() {
        template.convertAndSend(DIRECT_EXCHANGE, ROUTINGKEY_A, "A");
        template.convertAndSend(DIRECT_EXCHANGE, ROUTINGKEY_B, "B");

    }


    @Test
    public void work() {
        for (int i = 0; i < 20; i++) {
            template.convertAndSend(WORK_QUEUE, "axiba" + i);

        }
    }
    @Test
    public void work22() {
            template.convertAndSend("work22", "axiba" );

    }
    @Test
    public void fanout() {
        for (int i = 0; i < 20; i++) {
            template.convertAndSend("logs", "", "axiba" + i);
        }
    }

    @Test
    public void direct1() {
        for (int i = 0; i < 20; i++) {
            template.convertAndSend("direct1", "A", "axiba" + i);
        }
    }
}
