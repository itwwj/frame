package com.github.mongo.config;

import cn.hutool.core.lang.Assert;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.BasicMongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;

import java.util.HashMap;

/**
 * @author jie
 */
public class MongoDbFactory extends SimpleMongoDbFactory {


    /**
     * 默认数据库名称
     **/
    private final String defaultName;

    /**
     * MongoDB模板类
     **/
    private MongoTemplate mongoTemplate;

    /**
     * 用户所在线程使用数据库集合
     **/
    private static final ThreadLocal<String> dbName = new ThreadLocal<String>();
    /**
     *
     **/
    private static final HashMap<String, Object> databaseIndexMap = new HashMap<String, Object>();

    public MongoDbFactory(final MongoClient mongo, final String defaultDatabaseName) {
        super(mongo, defaultDatabaseName);
        this.defaultName = defaultDatabaseName;
    }

    /**
     * 功能描述:  dirty but ... what can I do?
     *
     * @param
     * @author jei0439
     * @date 2018/9/17 10:55
     */
    public void setMongoTemplate(final MongoTemplate mongoTemplate) {
        Assert.isNull(this.mongoTemplate, "You can set MongoTemplate just once");
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 功能描述: 将databaseName放入dbName
     *
     * @param databaseName database/scheme名称
     * @author jei0439
     * @date 2018/9/17 10:56
     */
    public static void setDatabaseNameForCurrentThread(final String databaseName) {
        dbName.set(databaseName);
    }

    /**
     * 功能描述: 清空dbName
     *
     * @author jei0439
     * @date 2018/9/17 10:57
     */
    public static void clearDatabaseNameForCurrentThread() {
        dbName.remove();
    }

    @Override
    public MongoDatabase getDb() {
        final String tlName = dbName.get();
        final String dbToUse = (tlName != null ? tlName : this.defaultName);
        createIndexIfNecessaryFor(dbToUse);
        return super.getDb(dbToUse);
    }

    /**
     * 功能描述: TODO: 没搞明白作用
     *
     * @param database database/scheme 名称
     * @author jei0439
     * @date 2018/9/17 10:58
     */
    private void createIndexIfNecessaryFor(final String database) {
        if (this.mongoTemplate == null) {
            return;
        }
//        sync and init once
        boolean needsToBeCreated = false;
        synchronized (MongoDbFactory.class) {
            final Object syncObj = databaseIndexMap.get(database);
            if (syncObj == null) {
                databaseIndexMap.put(database, new Object());
                needsToBeCreated = true;
            }
        }
//        make sure only one thread enters with needsToBeCreated = true
        synchronized (databaseIndexMap.get(database)) {
            if (needsToBeCreated) {
                createIndexes();
            }
        }
    }

    private void createIndexes() {
        final MongoMappingContext mappingContext = (MongoMappingContext) this.mongoTemplate.getConverter().getMappingContext();
        final MongoPersistentEntityIndexResolver indexResolver = new MongoPersistentEntityIndexResolver(mappingContext);
        for (BasicMongoPersistentEntity<?> persistentEntity : mappingContext.getPersistentEntities()) {
            checkForAndCreateIndexes(indexResolver, persistentEntity);
        }
    }

    private void checkForAndCreateIndexes(final MongoPersistentEntityIndexResolver indexResolver, final MongoPersistentEntity<?> entity) {
//        make sure its a root document
        if (entity.findAnnotation(Document.class) != null) {
            for (MongoPersistentEntityIndexResolver.IndexDefinitionHolder indexDefinitionHolder : indexResolver.resolveIndexFor(entity.getTypeInformation())) {
//                work because of javas reentered lock feature
                this.mongoTemplate.indexOps(entity.getType()).ensureIndex(indexDefinitionHolder);
            }
        }
    }
}
