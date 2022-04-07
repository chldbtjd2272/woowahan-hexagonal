package org.cys.hexagonal.food.order.adapter.out;

import org.cys.hexagonal.food.order.domain.Order;
import org.cys.hexagonal.food.order.domain.OrderLineItem;
import org.cys.hexagonal.food.order.domain.OrderOption;
import org.cys.hexagonal.food.order.domain.OrderOptionGroup;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class OrderJpaMapper {

    //jpa 변환
    public OrderJpaEntity mapFrom(Order order) {
        return new OrderJpaEntity(
                order.getId(),
                order.getUserId(),
                order.getShopId(),
                order.getOrderLineItems()
                        .stream()
                        .map(this::toOrderLineItem)
                        .collect(toList()),
                order.getOrderedTime(),
                order.getOrderStatus());
    }

    private OrderLineItemJpaEntity toOrderLineItem(OrderLineItem orderLineItem) {
        return new OrderLineItemJpaEntity(
                orderLineItem.getId(),
                orderLineItem.getMenuId(),
                orderLineItem.getName(),
                orderLineItem.getCount(),
                orderLineItem.getGroups()
                        .stream()
                        .map(this::toOrderOptionGroup)
                        .collect(Collectors.toList()));
    }

    private OrderOptionGroupJpaEntity toOrderOptionGroup(OrderOptionGroup orderOptionGroup) {
        return new OrderOptionGroupJpaEntity(
                orderOptionGroup.getId(),
                orderOptionGroup.getName(),
                orderOptionGroup.getOrderOptions()
                        .stream()
                        .map(this::toOrderOption)
                        .collect(Collectors.toList()));
    }

    private OrderOptionJpaEmbeddable toOrderOption(OrderOption orderOption) {
        return new OrderOptionJpaEmbeddable(
                orderOption.getName(),
                orderOption.getPrice());
    }


    //order 도메인 변환
    public Order mapFrom(OrderJpaEntity order) {
        return new Order(
                order.getId(),
                order.getUserId(),
                order.getShopId(),
                order.getOrderLineItems()
                        .stream()
                        .map(this::toOrderLineItem)
                        .collect(toList()),
                order.getOrderedTime(),
                order.getOrderStatus());
    }

    private OrderLineItem toOrderLineItem(OrderLineItemJpaEntity orderLineItem) {
        return new OrderLineItem(
                orderLineItem.getId(),
                orderLineItem.getMenuId(),
                orderLineItem.getName(),
                orderLineItem.getCount(),
                orderLineItem.getGroups()
                        .stream()
                        .map(this::toOrderOptionGroup)
                        .collect(Collectors.toList()));
    }

    private OrderOptionGroup toOrderOptionGroup(OrderOptionGroupJpaEntity orderOptionGroup) {
        return new OrderOptionGroup(
                orderOptionGroup.getId(),
                orderOptionGroup.getName(),
                orderOptionGroup.getOrderOptions()
                        .stream()
                        .map(this::toOrderOption)
                        .collect(Collectors.toList()));
    }

    private OrderOption toOrderOption(OrderOptionJpaEmbeddable orderOption) {
        return new OrderOption(
                orderOption.getName(),
                orderOption.getPrice());
    }
}
