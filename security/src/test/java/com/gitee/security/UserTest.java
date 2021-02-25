package com.gitee.security;


import com.gitee.security.entity.User;
import com.gitee.security.service.UserService;
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
public class UserTest {


    @Autowired
    private UserService service;

    @Test
    public void select() {
        User byId = service.getById(1);
        System.out.println(byId);
    }

}
