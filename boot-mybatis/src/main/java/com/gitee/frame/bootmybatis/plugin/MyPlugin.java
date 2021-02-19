package com.gitee.frame.bootmybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * 默认情况下MyBatis 允许使用插件来拦截的方法调用包括：
 * Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)  sql执行
 * ParameterHandler (getParameterObject, setParameters)  参数映射
 * ResultSetHandler (handleResultSets, handleOutputParameters) 处理结果和映射
 * StatementHandler (prepare, parameterize, batch, update, query) sql解析
 * <p>
 * <p>
 * <p>
 * Intercepts注解 用于拦截哪个对象的哪个方法 插件签名
 * <p>
 * Signature注解
 *      type 拦截的类
 *      method 拦截的方法
 *      args 拦截的参数
 *
 * @author jie
 */



//统计sql执行时间
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {


    private long time;
    /**
     * 拦截目标对象的目标方法执行
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //通过StatementHandler获取执行的sql
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        long start = System.currentTimeMillis();
        Object proceed = invocation.proceed();
        long end = System.currentTimeMillis();
        if ((end - start) > time) {
            System.out.println("本次数据库操作是慢查询，sql是:" + sql);
        }
        return proceed;
    }

    /**
     * 包装目标对象  为目标对象创建代理对象
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        //Plugin mybatis自带的代理 可以返回包装对象
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    /**
     * 将插件注册时的properties属性设置进来
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        this.time = Long.parseLong(properties.getProperty("time"));
    }
}
