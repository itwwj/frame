package com.gitee.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.mp.entity.Dept;
import com.gitee.mp.entity.Employee;
import com.gitee.mp.mapper.DeptMapper;
import com.gitee.mp.mapper.EmployeeMapper;
import com.gitee.mp.service.DeptService;
import com.gitee.mp.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author jie
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
