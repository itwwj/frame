package com.github.jpa.repository;

import com.github.jpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jie
 */
public interface DepartmentDao extends JpaRepository<Department,Integer> {
}
