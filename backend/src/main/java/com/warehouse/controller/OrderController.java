package com.warehouse.controller;

import com.warehouse.entity.Order;

import com.warehouse.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ADD ORDER
    @PostMapping
    public Order saveOrder(
            @RequestBody Order order) {

        return orderService.saveOrder(order);

    }

    // GET ORDERS
    @GetMapping
    public List<Order> getOrders() {

        return orderService.getOrders();

    }

}