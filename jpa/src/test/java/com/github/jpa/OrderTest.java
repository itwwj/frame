package com.github.jpa;

import com.github.jpa.entity.Order;
import com.github.jpa.entity.User;
import com.github.jpa.repository.OrderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


/**
 * @author jie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
    @Autowired
    private OrderDao orderDao;
    @Test
    public void  save(){
        Order order = Order.builder()
                .orderName("测试1")
                .user(User.builder().id(7).build())
                .build();
        orderDao.save(order);
    }

    @Test
    public void select(){
        Optional<Order> order = orderDao.findById(11);
        Order od = order.get();
        System.out.println(od);
    }
}
