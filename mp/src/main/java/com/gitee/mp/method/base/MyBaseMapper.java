package com.gitee.mp.method.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author jie
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {


    Integer deleteAll();
}
