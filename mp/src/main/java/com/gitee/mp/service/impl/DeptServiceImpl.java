package com.gitee.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gitee.mp.entity.Dept;
import com.gitee.mp.mapper.DeptMapper;
import com.gitee.mp.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * @author jie
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}
