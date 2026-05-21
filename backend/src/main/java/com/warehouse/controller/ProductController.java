package com.warehouse.controller;

import com.warehouse.entity.Product;

import com.warehouse.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ADD PRODUCT
    @PostMapping
    public Product saveProduct(
            @RequestBody Product product) {

        return productService.saveProduct(product);

    }

    // GET PRODUCTS
    @GetMapping
    public List<Product> getProducts() {

        return productService.getProducts();

    }

}