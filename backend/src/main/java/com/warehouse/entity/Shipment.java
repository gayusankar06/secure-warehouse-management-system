package com.warehouse.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingNumber;

    private String orderName;

    private String currentLocation;

    private String deliveryStatus;

}