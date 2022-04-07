package org.cys.hexagonal.food.order.service;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.order.domain.Order;
import org.cys.hexagonal.food.order.domain.OrderDeliveredEvent;
import org.cys.hexagonal.food.order.domain.OrderPayedEvent;
import org.cys.hexagonal.food.order.service.port.out.LoadOrderPort;
import org.cys.hexagonal.food.order.service.port.out.OrderEventSenderPort;
import org.cys.hexagonal.food.order.service.port.out.UpdateOrderStatusPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProcessingService {
    private final LoadOrderPort loadOrderPort;
    private final UpdateOrderStatusPort updateOrderStatusPort;
    private final OrderEventSenderPort orderEventSenderPort;

    public Order payOrder(Long orderId) {
        Order order = loadOrderPort.findById(orderId);
        order.payed();
        updateOrderStatusPort.updateOrderStatus(order);       // Domain Event 발행을 위해
        orderEventSenderPort.send(new OrderPayedEvent(order));
        return order;
    }

    public Order deliverOrder(Long orderId) {
        Order order = loadOrderPort.findById(orderId);
        order.delivered();
        updateOrderStatusPort.updateOrderStatus(order);       // Domain Event 발행을 위해
        orderEventSenderPort.send(new OrderDeliveredEvent(order));
        return order;
    }
}
