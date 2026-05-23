package com.warehouse.dto;

import com.warehouse.entity.Product;
import lombok.Data;

@Data
public class ProductResponse {

    private Long id;
    private String productName;
    private String category;
    
    private Double sellingPrice;
    private Double supplierPrice;
    
    private String maskedPrice; 
    
    private Integer quantity;
    private String stockStatus;
    
    private String maskedCode;
    private Boolean secureFlag;

    public ProductResponse() {
    }

    public static ProductResponse from(Product product, String role) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setProductName(product.getProductName());
        response.setCategory(product.getCategory());
        response.setStockStatus(getStockStatus(product.getQuantity()));

        if ("ADMIN".equals(role)) {
            response.setSellingPrice(product.getSellingPrice());
            response.setSupplierPrice(product.getSupplierPrice());
            response.setQuantity(product.getQuantity());
            response.setMaskedCode(product.getMaskedCode());
            response.setSecureFlag(product.getSecureFlag());
            response.setMaskedPrice("₹ " + product.getSellingPrice());
        } else if ("SELLER".equals(role)) {
            response.setSellingPrice(product.getSellingPrice());
            // SELLER cannot see supplier price and secure flag
            response.setSupplierPrice(null);
            response.setSecureFlag(null);
            response.setQuantity(product.getQuantity());
            response.setMaskedCode(product.getMaskedCode());
            response.setMaskedPrice("₹ " + product.getSellingPrice());
        } else { // BUYER
            // BUYER can only see masked price and public product details
            response.setSellingPrice(null);
            response.setSupplierPrice(null);
            response.setQuantity(null);
            response.setMaskedCode(null);
            response.setSecureFlag(null);
            response.setMaskedPrice("₹ *****");
        }

        return response;
    }

    private static String getStockStatus(Integer quantity) {
        if (quantity == null) {
            return "Unknown";
        }
        if (quantity > 50) return "In Stock";
        if (quantity >= 10) return "Limited";
        if (quantity > 0) return "Low Stock";
        return "Out of Stock";
    }
}