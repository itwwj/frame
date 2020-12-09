package com.github.mongo.repository;

import com.github.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * TODO
 *
 * @author jie
 * @date 2020/12/6 21:58
 */
public interface UserDao extends MongoRepository<User,Integer> {
}
