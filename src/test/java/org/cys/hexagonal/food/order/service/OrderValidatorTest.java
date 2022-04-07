package org.cys.hexagonal.food.order.service;

import org.cys.hexagonal.food.generic.money.domain.Money;
import org.cys.hexagonal.food.order.domain.Order;
import org.cys.hexagonal.food.shop.domain.Menu;
import org.cys.hexagonal.food.shop.domain.Shop;
import org.cys.hexagonal.food.shop.service.port.in.GetOrderShopQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.cys.hexagonal.food.Fixtures.aMenu;
import static org.cys.hexagonal.food.Fixtures.aShop;
import static org.cys.hexagonal.food.Fixtures.anOptionGroupSpec;
import static org.cys.hexagonal.food.Fixtures.anOptionSpec;
import static org.cys.hexagonal.food.Fixtures.anOrder;
import static org.cys.hexagonal.food.Fixtures.anOrderLineItem;
import static org.cys.hexagonal.food.Fixtures.anOrderOption;
import static org.cys.hexagonal.food.Fixtures.anOrderOptionGroup;
import static org.mockito.Mockito.mock;

public class OrderValidatorTest {
    private OrderValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new OrderValidator(mock(GetOrderShopQuery.class));
    }

    @Test
    public void 가게_준비중인경우_실패() {
        Shop shop = aShop().open(false).build();

        assertThatThrownBy(() -> validator.validate(anOrder().build(), shop, new HashMap<>()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 최소주문금액_이하인경우_실패() {
        Order order = anOrder().items(Collections.singletonList(
                anOrderLineItem().count(1).groups(Collections.singletonList(
                        anOrderOptionGroup().options(Collections.singletonList(
                                anOrderOption().price(Money.wons(12000)).build())).build()))
                        .build()))
                .build();
        Shop shop = aShop().open(false).minOrderAmount(Money.wons(13000)).build();

        assertThatThrownBy(() -> validator.validate(order, shop, new HashMap<>()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 주문항목이_빈경우_실패() {
        Order order = anOrder().items(Collections.emptyList()).build();

        assertThatThrownBy(() -> validator.validate(order, aShop().build(), new HashMap<>()))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void 메뉴옵션그룹이_변경된경우_실패() {
        Menu menu = aMenu()
                .basic(anOptionGroupSpec().name("그룹명").build())
                .build();

        Order order = anOrder().items(Collections.singletonList(
                anOrderLineItem()
                        .groups(Collections.singletonList(anOrderOptionGroup().name("그룹명변경").build()))
                        .build()))
                .build();

        assertThatThrownBy(() -> validator.validate(order, aShop().build(), new HashMap<Long, Menu>() {{
            put(1L, menu);
        }})).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void 메뉴옵션이_변경된경우_실패() {
        Menu menu = aMenu()
                .basic(anOptionGroupSpec()
                        .options(Collections.singletonList(anOptionSpec().name("옵션").build()))
                        .build())
                .build();

        Order order = anOrder().items(Collections.singletonList(
                anOrderLineItem().groups(Collections.singletonList(
                        anOrderOptionGroup().options(Collections.singletonList(
                                anOrderOption().name("옵션변경").price(Money.wons(12000)).build())).build()))
                        .build()))
                .build();


        assertThatThrownBy(() -> validator.validate(order, aShop().build(), new HashMap<Long, Menu>() {{
            put(1L, menu);
        }})).isInstanceOf(IllegalStateException.class);
    }
}
