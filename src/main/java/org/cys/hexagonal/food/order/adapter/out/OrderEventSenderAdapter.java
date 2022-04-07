package org.cys.hexagonal.food.order.adapter.out;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.order.domain.OrderDeliveredEvent;
import org.cys.hexagonal.food.order.domain.OrderPayedEvent;
import org.cys.hexagonal.food.order.service.port.out.OrderEventSenderPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventSenderAdapter implements OrderEventSenderPort {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void send(OrderDeliveredEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public void send(OrderPayedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
