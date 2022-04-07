package org.cys.hexagonal.food.order.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cys.hexagonal.food.Fixtures.aShop;
import static org.cys.hexagonal.food.Fixtures.anOrder;

public class OrderTest {
    @Test
    public void 결제완료() {
        Order order = anOrder().status(OrderStatus.ORDERED).build();

        order.payed();

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.PAYED);
    }

    @Test
    public void 배송완료() {
        Order order = anOrder()
                .shopId(aShop().build().getId())
                .status(OrderStatus.PAYED)
                .build();

        order.delivered();

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.DELIVERED);
    }
}
