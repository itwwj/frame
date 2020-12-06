package com.github.jpa;

import com.github.jpa.entity.TestEntity;
import com.github.jpa.repository.TestDao;
import org.junit.Before;
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
public class JpaApi {

    @Autowired
    private TestDao testDao;

    @Test
    public void sava() {
        TestEntity entity = TestEntity.builder()
                .name("axiba2")
                .email("123456789")
                .info("0000")
                .build();
        testDao.save(entity);
    }

}
