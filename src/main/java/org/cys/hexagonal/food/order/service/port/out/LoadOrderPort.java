package org.cys.hexagonal.food.order.service.port.out;

import org.cys.hexagonal.food.order.domain.Order;

public interface LoadOrderPort {
    Order findById(Long Id);
}
