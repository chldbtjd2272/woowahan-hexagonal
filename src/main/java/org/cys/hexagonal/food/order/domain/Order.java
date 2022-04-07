package org.cys.hexagonal.food.order.domain;

import lombok.Builder;
import lombok.Getter;
import org.cys.hexagonal.food.generic.money.domain.Money;
import org.cys.hexagonal.food.order.service.OrderValidator;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class Order extends AbstractAggregateRoot<Order> {

    private Long id;

    private Long userId;

    private Long shopId;

    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    private LocalDateTime orderedTime;

    private OrderStatus orderStatus;

    public Order(Long userId, Long shopId, List<OrderLineItem> items) {
        this(null, userId, shopId, items, LocalDateTime.now(), null);
    }

    @Builder
    public Order(Long id, Long userId, Long shopId, List<OrderLineItem> items, LocalDateTime orderedTime, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderedTime = orderedTime;
        this.orderStatus = status;
        this.orderLineItems.addAll(items);
    }

    public List<Long> getMenuIds() {
        return orderLineItems.stream().map(OrderLineItem::getMenuId).collect(toList());
    }

    public void place(OrderValidator orderValidator) {
        orderValidator.validate(this);
        ordered();
    }

    private void ordered() {
        this.orderStatus = OrderStatus.ORDERED;
    }

    public void payed() {
        this.orderStatus = OrderStatus.PAYED;
        registerEvent(new OrderPayedEvent(this));
    }

    public void delivered() {
        this.orderStatus = OrderStatus.DELIVERED;
        registerEvent(new OrderDeliveredEvent(this));
    }

    public Money calculateTotalPrice() {
        return Money.sum(orderLineItems, OrderLineItem::calculatePrice);
    }
}
