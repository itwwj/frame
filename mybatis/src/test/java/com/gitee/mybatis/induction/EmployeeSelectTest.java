package com.gitee.mybatis.induction;

import com.gitee.mybatis.induction.entity.Employee;
import com.gitee.mybatis.induction.entity.EmployeePlus;
import com.gitee.mybatis.induction.mapper.EmploeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
public class EmployeeSelectTest {
    /**
     * SqlSession非线程安全 测试可以这样使用  切记开发环境不可以这样
     * 开发环境必须从sqlSessionFactory获取
     */
    SqlSession sqlSession;
    EmploeeMapper mapper;

    @Before
    public void sqlSession() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(EmploeeMapper.class);
    }

    @After
    public void close() {
        sqlSession.close();
    }

    @Test
    public void selectToMap() {
        Map<Long, Employee> axiba = mapper.selectToMap("axiba");
        System.out.println(axiba);
    }

    /**
     * 级联属性查询
     */
    @Test
    public void selectEmpPlus() {
        EmployeePlus employeePlus = mapper.selectEmpPlus(10L);
        System.out.println(employeePlus);
    }

    @Test
    public void selectByDynamic() {
        Employee employee = new Employee();
        employee.setName("axiba");
        List<Employee> employees = mapper.selectByDynamic(employee);
        System.out.println(employees);
    }

    @Test
    public void updataByDynamic() {
        Employee employee = new Employee();
        employee.setName("aaaa");
        int i = mapper.updataByDynamic(10L, employee);
        System.out.println(i);
    }

    @Test
    public void selectInIds() {
        List<Employee> employees = mapper.selectInIds(Arrays.asList(8L, 9L, 10L, 11L));
        System.out.println(employees);
    }
}
