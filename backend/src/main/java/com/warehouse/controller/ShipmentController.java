package com.warehouse.controller;

import com.warehouse.entity.Shipment;

import com.warehouse.service.ShipmentService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@CrossOrigin("*")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    // ADD SHIPMENT
    @PostMapping
    public Shipment saveShipment(
            @RequestBody Shipment shipment) {

        return shipmentService
                .saveShipment(shipment);

    }

    // GET SHIPMENTS
    @GetMapping
    public List<Shipment> getShipments() {

        return shipmentService
                .getShipments();

    }

}