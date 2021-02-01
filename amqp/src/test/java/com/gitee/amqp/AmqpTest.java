package com.gitee.amqp;

import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jie
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void pub() {
        rabbitTemplate.convertAndSend("", "", "");
    }

}
