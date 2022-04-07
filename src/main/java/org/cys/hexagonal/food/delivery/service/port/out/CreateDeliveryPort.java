package org.cys.hexagonal.food.delivery.service.port.out;

import org.cys.hexagonal.food.delivery.domain.Delivery;

public interface CreateDeliveryPort {
    Delivery createDelivery(Delivery delivery);
}
