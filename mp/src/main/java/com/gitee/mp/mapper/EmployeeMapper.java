package com.gitee.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gitee.mp.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author jie
 */
public interface EmployeeMapper extends BaseMapper<Employee> {


    @Select("select * from tb_emp ${ew.customSqlSegment}")
    List<Employee> selectAll(@Param(Constants.WRAPPER) Wrapper<Employee> wrapper);
}
