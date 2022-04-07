package org.cys.hexagonal.food.money.domain;

import org.cys.hexagonal.food.generic.money.domain.Money;
import org.cys.hexagonal.food.generic.money.domain.Ratio;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RatioTest {

    @Test
    public void 퍼센트() {
        Ratio tenPercent = Ratio.valueOf(0.1);
        Money thousandWon = Money.wons(1000);

        assertThat(tenPercent.of(thousandWon)).isEqualTo(Money.wons(100));
    }
}
