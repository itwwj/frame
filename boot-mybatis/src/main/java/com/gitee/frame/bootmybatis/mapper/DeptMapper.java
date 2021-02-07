package com.gitee.frame.bootmybatis.mapper;


import com.gitee.frame.bootmybatis.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jie
 */
@Mapper
public interface DeptMapper {

    Dept findDeptByid(Long id);

    List<Dept> findAll();

    int insert(Dept dept);
}
