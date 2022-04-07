package org.cys.hexagonal.food.order.service;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.order.domain.Order;
import org.cys.hexagonal.food.order.domain.OrderDeliveredEvent;
import org.cys.hexagonal.food.order.domain.OrderPayedEvent;
import org.cys.hexagonal.food.order.service.port.in.DeliverOrder;
import org.cys.hexagonal.food.order.service.port.in.PayOrder;
import org.cys.hexagonal.food.order.service.port.out.OrderEventSenderPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;


@Component
@RequiredArgsConstructor
public class OrderProcessingFacade implements PayOrder, DeliverOrder {

    private final OrderProcessingService orderProcessingService;
    private final TransactionTemplate transactionTemplate;
    private final OrderEventSenderPort orderEventSenderPort;

    @Override
    public void deliverOrder(Long orderId) {
        Order order = transactionTemplate.execute(it -> orderProcessingService.deliverOrder(orderId));
        orderEventSenderPort.send(new OrderDeliveredEvent(order));
    }

    @Override
    public void payOrder(Long orderId) {
        Order order = orderProcessingService.payOrder(orderId);
        orderEventSenderPort.send(new OrderPayedEvent(order));
    }
}
