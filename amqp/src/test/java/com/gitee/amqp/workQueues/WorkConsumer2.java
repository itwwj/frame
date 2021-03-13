package com.gitee.amqp.workQueues;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author jie
 */
public class WorkConsumer2 {


    public static void main(String[] args) throws IOException, TimeoutException {
        //设置mq连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接主机
        connectionFactory.setHost("dc3-rabbitmq");
        //设置端口
        connectionFactory.setPort(5672);
        //设置虚拟主机
        connectionFactory.setVirtualHost("iot");
        //设置连接账号和密码
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        //连接mq服务 获取连接对象
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        channel.basicQos(1);
        /**
         * 通道绑定消息队列
         * 参数：
         * 1.队列名，如果队列不存在就创建
         * 2.用来定义队列特性是否要持久化 true持久化 false不持久化
         * 3.exclusive是否独占队列 true独占队列
         * 4.是否消费完后删除队列 true 自动删除
         * 5.附加参数
         */
        channel.queueDeclare("work", false, false, false, null);

        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
                //参数1:确认队列中哪个具体消息，参数2:是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
