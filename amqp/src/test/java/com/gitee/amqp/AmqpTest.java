package com.gitee.amqp;

import com.gitee.amqp.pub.PubService;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    private PubService service;

    @Test
    public void pub() {
        service.directSend("axiba");
    }

    @Test
    public void pub1(){
        service.fanoutSend("fanout");
    }

}
