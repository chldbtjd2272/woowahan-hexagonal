package org.cys.hexagonal.food.billing.domain;

import lombok.Builder;
import lombok.Getter;
import org.cys.hexagonal.food.generic.money.domain.Money;

@Getter
public class Billing {

    private Long id;

    private Long shopId;

    private Money commission = Money.ZERO;

    public Billing(Long shopId) {
        this(null, shopId, Money.ZERO);
    }

    @Builder
    public Billing(Long id, Long shopId, Money commission) {
        this.id = id;
        this.shopId = shopId;
        this.commission = commission;
    }


    public void billCommissionFee(Money commission) {
        this.commission = this.commission.plus(commission);
    }
}

