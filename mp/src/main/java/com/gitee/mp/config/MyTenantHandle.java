package com.gitee.mp.config;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * 多租户配置器
 *
 * @author jie
 */
public class MyTenantHandle implements TenantLineHandler {
    /**
     * 拦截字段值
     * @return
     */
    @Override
    public Expression getTenantId() {
        return new LongValue(1088248166370832385L);
    }

    /**
     * 拦截字段名
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        return "manager_id";
    }

    /**
     * 拦截表名
     * @param tableName
     * @return
     */
    @Override
    public boolean ignoreTable(String tableName) {
        if ("tb_user".equals(tableName)) {
            return false;
        }
        return true;
    }
}
