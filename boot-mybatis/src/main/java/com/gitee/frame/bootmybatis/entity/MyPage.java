package com.gitee.frame.bootmybatis.entity;

import lombok.Data;

/**
 * @author jie
 */
@Data
public class MyPage {
    private int pageSize;//每页显示条数
    private int pageCurrentPage;//第几页
    private int pageBegin;//开始位置
    private int numCount;//总条数
    private int pageTotal;//总条数
}
