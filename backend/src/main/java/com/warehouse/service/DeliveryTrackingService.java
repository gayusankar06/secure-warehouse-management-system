package com.warehouse.service;

import com.warehouse.entity.DeliveryTracking;
import com.warehouse.repository.DeliveryTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryTrackingService {

    @Autowired
    private DeliveryTrackingRepository repository;

    public DeliveryTracking saveTracking(DeliveryTracking tracking) {
        return repository.save(tracking);
    }

    public List<DeliveryTracking> getTrackingByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }
}
