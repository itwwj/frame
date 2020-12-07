package com.github.jpa;

import com.github.jpa.entity.Template;
import com.github.jpa.repository.TemplateDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author jie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateTest {

    @Autowired
    private TemplateDao templateDao;

    @Test
    public void sava() {
        Template build = Template.builder()
                .name("axiba")
                .age(10)
                .creaet(new Date())
                .build();
        templateDao.save(build);
    }
}
