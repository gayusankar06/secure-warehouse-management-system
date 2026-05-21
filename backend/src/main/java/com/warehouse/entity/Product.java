package com.warehouse.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Double price;

    private Integer quantity;

}