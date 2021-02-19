package com.gitee.frame.bootmybatis.plugin;

import com.gitee.frame.bootmybatis.entity.MyPage;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author jie
 */
@Intercepts({
        @Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class})
})
public class PageIntercept implements Interceptor {

    /**
     * 插件运行的代码，它将代替原有的方法，要重写最重要的intercept方法
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(invocation.getTarget() instanceof StatementHandler){
            //这里我们有一个设定  如果查询方法含有Page 就进行分页 其他方法无视
            //所以就要获取方法名
            StatementHandler statementHandler=(StatementHandler)invocation.getTarget();
            MetaObject metaObject=SystemMetaObject.forObject(statementHandler);
            MappedStatement mappedStatement=(MappedStatement)metaObject.getValue("delegate.mappedStatement");
            String selectId=mappedStatement.getId();
            if(selectId.matches(".*Page$")){
                BoundSql boundSql=(BoundSql)metaObject.getValue("delegate.BoundSql");
                String sql=boundSql.getSql();
                HashMap<String,Object> hashMap=(HashMap<String, Object>)(boundSql.getParameterObject());
                MyPage myPage=(MyPage)hashMap.get("page");
                //重写SQL
                String countSql="select count(0) from "+sql;
                String pageSql=sql+"limit "+myPage.getPageBegin()+","+myPage.getPageSize();

                Connection connection=(Connection)invocation.getArgs()[0];

                PreparedStatement cStatement=null;
                ResultSet rs=null;
                int totalCount=0;
                try {
                    cStatement=connection.prepareStatement(countSql);
                    setParameters(cStatement,mappedStatement,boundSql,boundSql.getParameterObject());
                    rs=cStatement.executeQuery();
                    if(rs.next()){
                        totalCount=rs.getInt(1);
                    }
                } catch (Exception e) {
                }finally {
                    try {
                        rs.close();
                        cStatement.close();
                    } catch (Exception e) {
                    }
                }
                metaObject.setValue("delegate.boundSql.sql", pageSql);

                myPage.setNumCount(totalCount);
            }


        }

        return invocation.proceed();
    }

    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
                               Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }

    }
    @Override
    public Object plugin(Object arg0) {
        //
        return null;
    }
@Override
    public void setProperties(Properties arg0) {
        //

    }


}
