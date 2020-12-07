package com.github.jpa;

import com.github.jpa.entity.User;
import com.github.jpa.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO
 *
 * @author jie
 * @date 2020/12/6 21:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void sava() {
        User entity = User.builder()
                .name("axiba2")
                .email("123456789")
                .info("0000")
                .build();
        userDao.save(entity);
    }

    @Test
    public void del() {
        User entity = User.builder()
                .id(7)
                .build();
        userDao.delete(entity);
    }
}
