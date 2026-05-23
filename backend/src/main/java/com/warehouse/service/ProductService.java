package com.warehouse.service;

import com.warehouse.entity.Product;

import com.warehouse.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

import com.warehouse.util.MaskingUtil;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){

        // GENERATE MASKED CODE
        product.setMaskedCode(

                MaskingUtil.generateMaskedCode()

        );

        return productRepository.save(product);

    }

    // GET ALL PRODUCTS
    public List<Product> getProducts() {

        return productRepository.findAll();

    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(productDetails.getProductName());
        product.setCategory(productDetails.getCategory());
        product.setSellingPrice(productDetails.getSellingPrice());
        product.setSupplierPrice(productDetails.getSupplierPrice());
        product.setQuantity(productDetails.getQuantity());
        product.setSecureFlag(productDetails.getSecureFlag());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}