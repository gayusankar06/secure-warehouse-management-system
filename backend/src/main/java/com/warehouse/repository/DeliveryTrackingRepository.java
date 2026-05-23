package com.warehouse.repository;

import com.warehouse.entity.DeliveryTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeliveryTrackingRepository extends JpaRepository<DeliveryTracking, Long> {

    List<DeliveryTracking> findByOrderId(Long orderId);

}
