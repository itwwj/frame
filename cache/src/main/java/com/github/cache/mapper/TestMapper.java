package com.github.cache.mapper;

import com.github.cache.entity.TestEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * @author jie
 */
@Mapper
public interface TestMapper {

    @Select("select * from tb_test where id=#{id}")
    TestEntity findById(Long id);

    @Insert("insert into tb_test(id,name,age) values(#{id},#{name},#{age})")
    void insert(TestEntity entity);

    @Update("updata tb_test set name=#{name},age=#{age} where id=#{id}")
    TestEntity updata(TestEntity entity);

    @Delete("delete from tb_test where id=#{id}")
    void del(Long id);

    @Select("select * from tb_test where name=#{name}")
    TestEntity findByName(String name);
}
