package org.cys.hexagonal.food.billing.adapter.in;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.billing.service.port.in.BillShop;
import org.cys.hexagonal.food.order.domain.OrderDeliveredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillShopWithOrderDeliveredEventHandler {
    private final BillShop billShop;

    @Async
    @EventListener
    public void handle(OrderDeliveredEvent event) {
        billShop.bill(event.getShopId(), event.getTotalPrice());
    }
}
