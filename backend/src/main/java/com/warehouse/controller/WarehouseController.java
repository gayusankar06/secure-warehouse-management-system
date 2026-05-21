package com.warehouse.controller;

import com.warehouse.entity.Warehouse;

import com.warehouse.service.WarehouseService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@CrossOrigin("*")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    // ADD WAREHOUSE
    @PostMapping
    public Warehouse saveWarehouse(
            @RequestBody Warehouse warehouse) {

        return warehouseService
                .saveWarehouse(warehouse);

    }

    // GET WAREHOUSES
    @GetMapping
    public List<Warehouse> getWarehouses() {

        return warehouseService
                .getWarehouses();

    }

}