package org.cys.hexagonal.food.order.service.port.out;

import org.cys.hexagonal.food.order.domain.OrderDeliveredEvent;
import org.cys.hexagonal.food.order.domain.OrderPayedEvent;

public interface OrderEventSenderPort {

    void send(OrderDeliveredEvent order);

    void send(OrderPayedEvent order);
}
