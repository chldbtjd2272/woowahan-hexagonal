package org.cys.hexagonal.food.delivery.adapter.in;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.delivery.service.port.in.CompleteDelivery;
import org.cys.hexagonal.food.order.domain.OrderDeliveredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompleteDeliveryWithOrderDeliveredEventHandler {
    private final CompleteDelivery completeDelivery;


    @Async
    @EventListener
    public void handle(OrderDeliveredEvent event) {
        completeDelivery.complete(event.getOrderId());
    }
}
