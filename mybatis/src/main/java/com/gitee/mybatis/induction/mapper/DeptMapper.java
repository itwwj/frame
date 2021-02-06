package com.gitee.mybatis.induction.mapper;

import com.gitee.mybatis.induction.entity.Dept;
import com.gitee.mybatis.induction.entity.DeptPlus;
import org.apache.ibatis.annotations.Insert;

/**
 * @author jie
 */
public interface DeptMapper {

    DeptPlus findDeptPlusByid(Long id);

    @Insert("insert into tb_dept (d_name) values(#{dName})")
    int insert(Dept dept);
}
