package com.gitee.frame.bootmybatis;

import com.gitee.frame.bootmybatis.entity.Dept;
import com.gitee.frame.bootmybatis.mapper.DeptMapper;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class BootMybatisApplicationTests {

    @Autowired
    private DeptMapper deptMapper;

    @Test
    void test() {
        PageHelper.startPage(1, 10);
        Dept deptByid = deptMapper.findDeptByid(2L);

        System.out.println(deptByid);
    }


    @Test
    public void findAll() {
        PageHelper.startPage(1, 4);
        List<Dept> all = deptMapper.findAll();
        System.out.println(all);
    }

    @Autowired
    private SqlSession bathSqlsession;

    @Test
    public void insert() {
        DeptMapper mapper = bathSqlsession.getMapper(DeptMapper.class);
        for (int i = 0; i < 1000; i++) {
            mapper.insert(new Dept(0L, UUID.randomUUID().toString().substring(0, 5)));
        }
        bathSqlsession.commit();
    }
}
