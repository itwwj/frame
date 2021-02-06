package com.gitee.frame.bootmybatis;

import com.gitee.frame.bootmybatis.entity.Dept;
import com.gitee.frame.bootmybatis.mapper.DeptMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootMybatisApplicationTests {

    @Autowired
    private DeptMapper deptMapper;
    @Test
    void test() {
        Dept deptByid = deptMapper.findDeptByid(2L);
        System.out.println(deptByid);
    }

}
