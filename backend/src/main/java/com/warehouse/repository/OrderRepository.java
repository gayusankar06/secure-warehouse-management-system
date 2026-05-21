package com.warehouse.repository;

import com.warehouse.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

}