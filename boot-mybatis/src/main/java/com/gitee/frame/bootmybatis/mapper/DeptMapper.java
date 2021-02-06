package com.gitee.frame.bootmybatis.mapper;


import com.gitee.frame.bootmybatis.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jie
 */
@Mapper
public interface DeptMapper {

    Dept findDeptByid(Long id);
}
