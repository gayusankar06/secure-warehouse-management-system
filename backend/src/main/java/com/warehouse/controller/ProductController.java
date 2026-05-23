package com.warehouse.controller;

import com.warehouse.dto.ProductResponse;
import com.warehouse.entity.Product;
import com.warehouse.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<ProductResponse> getProducts(
            Authentication authentication) {

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_"))
                .map(auth -> auth.substring(5))
                .findFirst()
                .orElse("BUYER");

        return productService.getProducts().stream()
                .map(product -> ProductResponse.from(product, role))
                .collect(Collectors.toList());

    }

    // UPDATE PRODUCT
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product productDetails) {
        
        return productService.updateProduct(id, productDetails);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}