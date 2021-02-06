package com.gitee.mybatis.induction;

import com.gitee.mybatis.induction.entity.Employee;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
public class MybatisTest {

    /**
     * SqlSession非线程安全 测试可以这样使用  切记开发环境不可以这样
     * 开发环境必须从sqlSessionFactory获取
     */
    SqlSession sqlSession;

    @Before
    public void sqlSession() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        //sqlSession = sqlSessionFactory.openSession(true); //自动提交

    }

    @After
    public void close() {
        sqlSession.close();
    }

    /**
     * 直接使用配置文件方式查询
     *
     * @throws IOException
     */
    @Test
    public void selectOne() throws IOException {
        Employee employee = sqlSession.selectOne("selectOne", 1);
        System.out.println(employee);
    }

    /**
     * 使用接口和配置文件进行映射
     * <p>
     * 原理：为接口创建代理对象 代理对象执行方法
     */
    @Test
    public void interfaceSelectOne() {
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        Employee employee = mapper.selectOne(1L);
        System.out.println(employee);
    }

    /**
     * 注解映射
     */
    @Test
    public void annotationSelectOne() {
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        Employee employee = mapper.findOne(2L);
        System.out.println(employee);
    }

    @Test
    public void insert() {
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        Employee employee = new Employee();
        employee.setName("axiba");
        employee.setAge(20);
        mapper.insert(employee);
        //一定注意提交
        sqlSession.commit();
    }

    @Test
    public void updata() {
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        Employee employee = new Employee();
        employee.setId(8L);
        employee.setName("bbbb");
        employee.setAge(20);
        mapper.updata(employee);
        //一定注意提交
        sqlSession.commit();
    }

    @Test
    public void delete() {
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        mapper.delete(8L);
        //一定注意提交
        sqlSession.commit();
    }

    @Test
    public void insertReturnKey() {
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        Employee employee = new Employee();
        employee.setName("axiba");
        employee.setAge(20);
        mapper.insertReturnKey(employee);
        System.out.println("主键=====》" + employee.getId());
        //一定注意提交
        sqlSession.commit();
    }

    @Test
    public void selectByExmaple() {
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        List<Employee> axiba = mapper.selectByNameAndAge("axiba", 20);
        System.out.println(axiba);
    }

    @Test
    public void selectByMap() {
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "axiba");
        map.put("age", 20);
        List<Employee> axiba = mapper.selectByMap(map);
        System.out.println(axiba);
    }

    @Test
    public void selectByEx() {
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        List<Employee> exmaple = mapper.selectByExmaple("axiba", new Employee(0L, "", 20, 0L));
        System.out.println(exmaple);
    }

    @Test
    public void selectByEntity() {
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        List<Employee> exmaple = mapper.selectByEntity(new Employee(0L, "axiba", 20, 0L));
        System.out.println(exmaple);
    }

    @Test
    public void selectByList() {
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        List<Object> list = new ArrayList<>();
        list.add("axiba");
        list.add(20);
        List<Employee> exmaple = mapper.selectByList(list);
        System.out.println(exmaple);
    }
}
