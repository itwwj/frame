package com.gitee.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gitee.mp.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author jie
 */
@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

}
