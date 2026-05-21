package com.warehouse.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    private Integer capacity;

}