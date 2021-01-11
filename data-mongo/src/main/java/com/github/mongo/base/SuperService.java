package com.github.mongo.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.lang.reflect.ParameterizedType;

/**
 * @author jie
 */
public abstract class SuperService<T>  implements BaseService<T>{
    @Autowired
    private MongoTemplate mongoTemplate;

    protected Class<T> entityClass = null;

    @Override
    public MongoTemplate getDao() {
        return mongoTemplate;
    }

    @Override
    public Class<T> getEntityClass() {
        if (entityClass == null) {
            this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return this.entityClass;
    }
}
