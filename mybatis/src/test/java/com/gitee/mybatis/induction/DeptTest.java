package com.gitee.mybatis.induction;

import com.gitee.mybatis.induction.entity.Dept;
import com.gitee.mybatis.induction.entity.DeptPlus;
import com.gitee.mybatis.induction.mapper.DeptMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jie
 */
public class DeptTest {
    /**
     * SqlSession非线程安全 测试可以这样使用  切记开发环境不可以这样
     * 开发环境必须从sqlSessionFactory获取
     */
    SqlSession sqlSession;
    DeptMapper mapper;

    @Before
    public void sqlSession() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(DeptMapper.class);
    }

    @After
    public void close() {
        sqlSession.close();
    }

    @Test
    public void insert() {
        int bbbb = mapper.insert(new Dept(0L, "bbbb"));
        System.out.println(bbbb);
    }

    @Test
    public void findDeptPlusByid() {
        DeptPlus deptPlusByid = mapper.findDeptPlusByid(2L);
        System.out.println(deptPlusByid);
    }
}
