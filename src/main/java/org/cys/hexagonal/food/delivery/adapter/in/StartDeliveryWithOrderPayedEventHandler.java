package org.cys.hexagonal.food.delivery.adapter.in;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.delivery.service.port.in.StartDelivery;
import org.cys.hexagonal.food.order.domain.OrderPayedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartDeliveryWithOrderPayedEventHandler {
    private StartDelivery startDelivery;

    @Async
    @EventListener
    public void handle(OrderPayedEvent event) {
        startDelivery.delivery(event.getOrderId());
    }
}
