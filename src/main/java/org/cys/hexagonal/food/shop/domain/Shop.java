package org.cys.hexagonal.food.shop.domain;

import lombok.Builder;
import lombok.Getter;
import org.cys.hexagonal.food.generic.money.domain.Money;
import org.cys.hexagonal.food.generic.money.domain.Ratio;

@Getter
public class Shop {
    private Long id;
    private String name;
    private boolean open;
    private Money minOrderAmount;
    private Ratio commissionRate;

    public Shop(String name, boolean open, Money minOrderAmount) {
        this(name, open, minOrderAmount, Ratio.valueOf(0));
    }

    public Shop(String name, boolean open, Money minOrderAmount, Ratio commissionRate) {
        this(null, name, open, minOrderAmount, commissionRate);
    }

    @Builder
    public Shop(Long id, String name, boolean open, Money minOrderAmount, Ratio commissionRate) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.minOrderAmount = minOrderAmount;
        this.commissionRate = commissionRate;
    }

    public boolean isValidOrderAmount(Money amount) {
        return amount.isGreaterThanOrEqual(minOrderAmount);
    }

    public void open() {
        this.open = true;
    }

    public void close() {
        this.open = true;
    }

    public void modifyCommissionRate(Ratio commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Money calculateCommissionFee(Money price) {
        return commissionRate.of(price);
    }
}
