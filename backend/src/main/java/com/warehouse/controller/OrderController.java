package com.warehouse.controller;

import com.warehouse.entity.Order;
import com.warehouse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ADD ORDER
    @PostMapping
    public Order saveOrder(
            @RequestBody Order order, Authentication authentication) {
        if (authentication != null && order.getBuyerName() == null) {
            order.setBuyerName(authentication.getName());
        }
        return orderService.saveOrder(order);
    }

    // GET ORDERS
    @GetMapping
    public List<Order> getOrders(Authentication authentication) {
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_"))
                .map(auth -> auth.substring(5))
                .findFirst()
                .orElse("BUYER");
                
        List<Order> orders = orderService.getOrders();
        
        if ("BUYER".equals(role)) {
            String username = authentication.getName();
            return orders.stream()
                .filter(o -> username.equals(o.getBuyerName()))
                .collect(Collectors.toList());
        }
        
        return orders;
    }
}