package com.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    private String category;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "supplier_price")
    private Double supplierPrice;

    private Integer quantity;

    @Column(name = "masked_code")
    private String maskedCode;

    @Column(name = "secure_flag")
    private Boolean secureFlag;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}