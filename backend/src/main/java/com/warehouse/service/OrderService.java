package com.warehouse.service;

import com.warehouse.entity.Order;

import com.warehouse.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // ADD ORDER
    public Order saveOrder(Order order) {

        return orderRepository.save(order);

    }

    // GET ORDERS
    public List<Order> getOrders() {

        return orderRepository.findAll();

    }

}