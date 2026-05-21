package com.warehouse.service;

import com.warehouse.entity.Shipment;

import com.warehouse.repository.ShipmentRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    // ADD SHIPMENT
    public Shipment saveShipment(
            Shipment shipment) {

        return shipmentRepository.save(shipment);

    }

    // GET SHIPMENTS
    public List<Shipment> getShipments() {

        return shipmentRepository.findAll();

    }

}