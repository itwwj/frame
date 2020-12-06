package com.github.jpa.repository;

import com.github.jpa.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO
 *
 * @author jie
 * @date 2020/12/6 21:58
 */
public interface TestDao extends JpaRepository<TestEntity,Integer> {
}
