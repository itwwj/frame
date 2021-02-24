package com.gitee.mp;

import com.gitee.mp.entity.Dept;
import com.gitee.mp.entity.Employee;
import com.gitee.mp.mapper.DeptMapper;
import com.gitee.mp.mapper.EmployeeMapper;
import com.gitee.mp.service.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author jie
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DeptTest {

    @Autowired
    private DeptMapper mapper;

    @Autowired
    private DeptService service;

    @Test
    public void inert() {
        mapper.insert(Dept.builder().dname(UUID.randomUUID().toString().substring(0,5)).build());
    }

    @Test
    public void select() {
        Dept employee = mapper.selectById(2L);
        System.out.println(employee);
    }
    @Test
    public void selectByService() {
        Dept byId = service.getById(10L);
        System.out.println(byId);
    }
}
