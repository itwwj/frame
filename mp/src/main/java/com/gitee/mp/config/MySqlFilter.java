package com.gitee.mp.config;

import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author jie
 */
public class MySqlFilter implements ISqlParserFilter {

    @Override
    public boolean doFilter(MetaObject metaObject) {
        MappedStatement mappedStatement = SqlParserHelper.getMappedStatement(metaObject);
        if ("com.gitee.mp.mapper.selectPage".equals(mappedStatement.getId())) {
            return false;
        }
        return true;
    }
}
