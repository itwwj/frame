package com.gitee.frame.bootmybatis.config;

import com.gitee.frame.bootmybatis.plugin.EnumPlugin;
import com.gitee.frame.bootmybatis.plugin.MyPlugin;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * @author jie
 */
@org.springframework.context.annotation.Configuration
public class MybatisConf {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                //插件拦截链采用了责任链模式，执行顺序和加入连接链的顺序有关
                MyPlugin myPlugin = new MyPlugin();
                //设置参数，比如阈值等，可以在配置文件中配置，这里直接写死便于测试
                Properties properties = new Properties();
                //这里设置慢查询阈值为1毫秒，便于测试
                properties.setProperty("time", "1");
                myPlugin.setProperties(properties);
                configuration.addInterceptor(myPlugin);

                //分页插件
                PageInterceptor pageInterceptor = new PageInterceptor();
                configuration.addInterceptor(pageInterceptor);

                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                typeHandlerRegistry.setDefaultEnumTypeHandler(EnumPlugin.class);
            }
        };
    }

    @Bean("bathSqlsession")
    public SqlSession bathSqlSession(SqlSessionFactory sqlSessionFactory) {
        return sqlSessionFactory.openSession(ExecutorType.BATCH);
    }


}
