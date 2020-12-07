package com.github.jpa.repository;

import com.github.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jie
 */
public interface OrderDao extends JpaRepository<Order,Integer> {
}
