package org.cys.hexagonal.food.delivery.service.port.out;

import org.cys.hexagonal.food.delivery.domain.Delivery;

public interface LoadDeliveryPort {
    Delivery loadDeliveryById(Long id);
}
