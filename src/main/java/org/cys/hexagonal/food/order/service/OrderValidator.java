package org.cys.hexagonal.food.order.service;

import org.cys.hexagonal.food.order.domain.Order;
import org.cys.hexagonal.food.order.domain.OrderLineItem;
import org.cys.hexagonal.food.order.domain.OrderOptionGroup;
import org.cys.hexagonal.food.shop.domain.Menu;
import org.cys.hexagonal.food.shop.domain.OptionGroupSpecification;
import org.cys.hexagonal.food.shop.domain.Shop;
import org.cys.hexagonal.food.shop.service.port.in.GetOrderShopQuery;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class OrderValidator {
    private final GetOrderShopQuery getOrderShopQuery;

    public OrderValidator(GetOrderShopQuery getOrderShopQuery) {
        this.getOrderShopQuery = getOrderShopQuery;
    }

    public void validate(Order order) {
        validate(order, getShop(order), getMenus(order));
    }

    void validate(Order order, Shop shop, Map<Long, Menu> menus) {
        if (!shop.isOpen()) {
            throw new IllegalArgumentException("가게가 영업중이 아닙니다.");
        }

        if (order.getOrderLineItems().isEmpty()) {
            throw new IllegalStateException("주문 항목이 비어 있습니다.");
        }

        if (!shop.isValidOrderAmount(order.calculateTotalPrice())) {
            throw new IllegalStateException(String.format("최소 주문 금액 %s 이상을 주문해주세요.", shop.getMinOrderAmount()));
        }

        for (OrderLineItem item : order.getOrderLineItems()) {
            validateOrderLineItem(item, menus.get(item.getMenuId()));
        }
    }

    private void validateOrderLineItem(OrderLineItem item, Menu menu) {
        if (!menu.getName().equals(item.getName())) {
            throw new IllegalArgumentException("기본 상품이 변경됐습니다.");
        }

        for (OrderOptionGroup group : item.getGroups()) {
            validateOrderOptionGroup(group, menu);
        }
    }

    private void validateOrderOptionGroup(OrderOptionGroup group, Menu menu) {
        for (OptionGroupSpecification spec : menu.getOptionGroupSpecs()) {
            if (spec.isSatisfiedBy(group.convertToOptionGroup())) {
                return;
            }
        }

        throw new IllegalArgumentException("메뉴가 변경됐습니다.");
    }

    private Shop getShop(Order order) {
        return getOrderShopQuery.findShopById(order.getShopId());
    }

    private Map<Long, Menu> getMenus(Order order) {
        return getOrderShopQuery.findMenusByMenuIds(order.getMenuIds()).stream().collect(toMap(Menu::getId, identity()));
    }
}