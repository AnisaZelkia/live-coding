package com.test.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.test.demo.persistence.entity.Order;

public interface OrderRepository    extends JpaRepository<Order, Integer>,
JpaSpecificationExecutor<Order> {

}
