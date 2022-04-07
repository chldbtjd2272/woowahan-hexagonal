package org.cys.hexagonal.food.shop.domain;

import org.cys.hexagonal.food.Fixtures;
import org.cys.hexagonal.food.generic.money.domain.Money;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MenuTest {

    @Test
    public void 메뉴이름_변경_오류() {
        Menu menu = Fixtures.aMenu().name("삼겹살").build();

        assertThatThrownBy(() -> menu.validateOrder("오겹살", Collections.singletonList(Fixtures.anOptionGroup().build())))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    public void 옵션그룹이름_변경_오류() {
        Menu menu = Fixtures.aMenu().basic(Fixtures.anOptionGroupSpec().name("기본").build()).build();

        assertThatThrownBy(() -> menu.validateOrder("", Collections.singletonList(Fixtures.anOptionGroup().name("기본 메뉴").build())))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    public void 옵션이름_변경_오류() {
        Menu menu = Fixtures.aMenu()
                .basic(Fixtures.anOptionGroupSpec().options(Collections.singletonList(Fixtures.anOptionSpec().name("1인분").build())).build())
                .build();

        assertThatThrownBy(() -> menu.validateOrder("", Collections.singletonList(Fixtures.anOptionGroup()
                .options(Collections.singletonList(Fixtures.anOption().name("혼밥").build())).build())))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    public void 옵션가격_변경_오류() {
        Menu menu = Fixtures.aMenu()
                .basic(Fixtures.anOptionGroupSpec()
                        .options(Collections.singletonList(Fixtures.anOptionSpec().name("1인분").price(Money.wons(12000)).build()))
                        .build())
                .build();

        assertThatThrownBy(() -> menu.validateOrder("", Collections.singletonList(Fixtures.anOptionGroup()
                .options(Collections.singletonList(Fixtures.anOption().name("1인분").price(Money.wons(10000)).build()))
                .build())))
                .isInstanceOf(IllegalArgumentException.class);

    }
}
