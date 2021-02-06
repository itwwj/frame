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

/**
 * mybatis一级缓存
 * 一级缓存是sqlssion级别的缓存
 * 一级缓存默认开启
 *
 * @author jie
 */
public class FirstCacheTest {

    /**
     * SqlSession非线程安全 测试可以这样使用  切记开发环境不可以这样
     * 开发环境必须从sqlSessionFactory获取
     */
    SqlSession sqlSession;
    EmploeeMapper mapper;
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void sqlSession() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(EmploeeMapper.class);
    }

    @After
    public void close() {
        sqlSession.close();
    }

    @Test
    public void firstCache() {
        Employee employee = mapper.selectOne(10L);
        System.out.println(employee);
        Employee employee1 = mapper.selectOne(10L);
        System.out.println(employee1);
    }

    @Test
    public void sqlssionCache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmploeeMapper mapper = sqlSession.getMapper(EmploeeMapper.class);
        Employee employee = mapper.selectOne(10L);
        System.out.println(employee);
        sqlSession.close();
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(EmploeeMapper.class);
        Employee employee1 = mapper.selectOne(10L);
        System.out.println(employee1);
        System.out.println(employee == employee1);
        sqlSession.close();
    }


}
