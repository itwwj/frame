package com.gitee.mp.method.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.gitee.mp.method.DeleteAll;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jie
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {


    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new DeleteAll());
        return methodList;
    }

}
