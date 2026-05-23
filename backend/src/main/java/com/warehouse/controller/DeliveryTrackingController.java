package com.warehouse.controller;

import com.warehouse.entity.DeliveryTracking;
import com.warehouse.service.DeliveryTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
@CrossOrigin("*")
public class DeliveryTrackingController {

    @Autowired
    private DeliveryTrackingService trackingService;

    @PostMapping
    public DeliveryTracking saveTracking(@RequestBody DeliveryTracking tracking) {
        return trackingService.saveTracking(tracking);
    }

    @GetMapping("/{orderId}")
    public List<DeliveryTracking> getTracking(@PathVariable Long orderId) {
        return trackingService.getTrackingByOrderId(orderId);
    }
}
