package com.gitee.mp;

import com.gitee.mp.entity.Employee;
import com.gitee.mp.mapper.EmployeeMapper;
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
public class EmployeeTest {

    @Autowired
    private EmployeeMapper mapper;


    @Test
    public void inert(){
        mapper.insert(new Employee(0L,"axiba",20,"00000000000"));
    }

    @Test
    public void select(){
        Employee employee = mapper.selectById(1L);
        System.out.println(employee );
    }
}
