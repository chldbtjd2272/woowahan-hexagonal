package org.cys.hexagonal.food.delivery.service.port.out;

import org.cys.hexagonal.food.delivery.domain.Delivery;

public interface UpdateDeliveryStatusPort {
    Delivery updateDeliveryStatus(Delivery delivery);
}
