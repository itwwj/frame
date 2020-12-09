package com.github.mongo.repository;

import com.github.mongo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void save(){
        User user = User.builder().name("axiba")
                .creaet(new Date())
                .email("123456")
                .info("")
                .build();
        userDao.save(user);
    }
}