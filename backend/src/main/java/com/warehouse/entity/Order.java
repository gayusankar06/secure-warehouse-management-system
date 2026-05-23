package com.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "product_id")
    private Long productId;

    private Integer quantity;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "tracking_id")
    private String trackingId;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "estimated_delivery")
    private LocalDate estimatedDelivery;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}