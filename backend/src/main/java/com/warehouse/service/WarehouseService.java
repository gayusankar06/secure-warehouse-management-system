package com.warehouse.service;

import com.warehouse.entity.Warehouse;

import com.warehouse.repository.WarehouseRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    // ADD WAREHOUSE
    public Warehouse saveWarehouse(
            Warehouse warehouse) {

        return warehouseRepository.save(warehouse);

    }

    // GET ALL WAREHOUSES
    public List<Warehouse> getWarehouses() {

        return warehouseRepository.findAll();

    }

}