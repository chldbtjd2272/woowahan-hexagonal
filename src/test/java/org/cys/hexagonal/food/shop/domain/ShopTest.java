package org.cys.hexagonal.food.shop.domain;

import org.cys.hexagonal.food.generic.money.domain.Money;
import org.cys.hexagonal.food.generic.money.domain.Ratio;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cys.hexagonal.food.Fixtures.aShop;

public class ShopTest {
    @Test
    public void 최소주문금액_체크() {
        Shop shop = aShop().minOrderAmount(Money.wons(15000)).build();

        assertThat(shop.isValidOrderAmount(Money.wons(14000))).isFalse();
        assertThat(shop.isValidOrderAmount(Money.wons(15000))).isTrue();
        assertThat(shop.isValidOrderAmount(Money.wons(16000))).isTrue();
    }

    @Test
    public void 수수료_계산() {
        Shop shop = aShop()
                .commissionRate(Ratio.valueOf(0.1))
                .build();

        assertThat(shop.calculateCommissionFee(Money.wons(1000))).isEqualTo(Money.wons(100));
    }
}
