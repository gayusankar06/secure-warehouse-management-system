package com.warehouse.service;

import com.warehouse.entity.Product;

import com.warehouse.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // SAVE PRODUCT
    public Product saveProduct(Product product) {

        return productRepository.save(product);

    }

    // GET ALL PRODUCTS
    public List<Product> getProducts() {

        return productRepository.findAll();

    }

}