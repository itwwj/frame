package com.github.mongo.base;

import cn.hutool.core.bean.BeanUtil;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.support.PageableExecutionUtils;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
public interface BaseService<T> {
    /**
     * 获取service
     *
     * @return
     */
    MongoTemplate getDao();

    /**
     * 获取实体的类型
     *
     * @return
     */
    Class<T> getEntityClass();
    /**
     * 增
     *
     * @param t
     */
    default T save(T t) {
        return (T) getDao().insert(t);
    }

    /**
     * 批量增加
     *
     * @param batchToSave
     * @return
     */
    default Collection<T> saveAll(List<T> batchToSave) {
        return getDao().insert(batchToSave, getEntityClass());
    }

    /**
     * 条件删除(物理删除)
     *
     * @param query
     * @return
     */
    default void delByQuery(Query query) {
        getDao().remove(query, getEntityClass());
    }


    /**
     * 改
     *
     * @param t
     */
    default T update(T t) {
        return (T) getDao().save(t);
    }

    /**
     * 批量修改
     *
     * @return
     */
    default UpdateResult updateByQuery(Query query, Update update) {
        return getDao().updateMulti(query, update, getEntityClass());
    }

    /**
     * 条件修改，当没有符合条件的文档，就以这个条件和更新文档为基础创建一个新的文档，如果找到匹配的文档就正常的更新
     *
     * @param query
     * @param t
     * @return
     */
    default UpdateResult upsert(Query query, T t) {
        Map<String, Object> map = BeanUtil.beanToMap(t);
        Update update = new Update();
        for (String s : map.keySet()) {
            if (map.get(s) != null) {
                update.set(s, map.get(s));
            }
        }
        return getDao().upsert(query, update, getEntityClass());
    }


    /**
     * 累加操作
     *
     * @param query
     * @return
     */
    default UpdateResult updateInc(Query query, String field, Number value) {
        Update update = new Update();
        update = update.inc(field, value);
        return updateByQuery(query, update);
    }

    /**
     * 查
     *
     * @param id
     * @return
     */
    default T findById(Object id) {
        return getDao().findOne(Query.query(Criteria.where("id").is(id)), getEntityClass());
    }

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    default Page<T> findPage(PageRequest pageRequest) throws ParseException {
        Query query = new Query();
        int count = (int) getDao().count(query, getEntityClass());
        List<T> list = getDao().find(query.with(pageRequest), getEntityClass());
        return PageableExecutionUtils.getPage(list, pageRequest, () -> count);
    }

    /**
     * 查询符合条件的数量
     *
     * @param query
     * @return
     */
    default Long findCountByQuery(Query query) {
        return getDao().count(query, getEntityClass());
    }

    /**
     * 条件查询分页查询
     *
     * @param pageRequest
     * @return
     */
    default Page<T> findPageByQuery(PageRequest pageRequest,Query query) throws ParseException {
        int count = (int) getDao().count(query, getEntityClass());
        List<T> list = getDao().find(query.with(pageRequest), getEntityClass());
        return PageableExecutionUtils.getPage(list, pageRequest, () -> count);
    }

    /**
     * 条件查询全部
     *
     * @param query
     * @return
     */
    default List<T> findByQuery(Query query) {
        return getDao().find(query, getEntityClass());
    }
}
