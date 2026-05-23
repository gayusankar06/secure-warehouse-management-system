package com.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "delivery_tracking")
public class DeliveryTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "courier_name")
    private String courierName;

    @Column(name = "current_location")
    private String currentLocation;

    @Column(name = "delivery_progress")
    private Integer deliveryProgress;

    @Column(name = "delivery_status")
    private String deliveryStatus;

}
