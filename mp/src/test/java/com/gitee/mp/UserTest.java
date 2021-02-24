package com.gitee.mp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gitee.mp.entity.User;
import com.gitee.mp.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author jie
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UserMapper mapper;


    @Test
    public void deleteById() {
        int i = mapper.deleteById(1088248166370832385L);
        System.out.println(i);
    }

    /**
     * 只有selectPage会有租户信息
     */
    @Test
    public void selectAll() {
        IPage<User> page=new Page<>(1,10);
        IPage<User> page1 = mapper.selectPage(page, Wrappers.lambdaQuery(User.class));
        page1.getRecords().stream().forEach(System.out::println);
    }


}
