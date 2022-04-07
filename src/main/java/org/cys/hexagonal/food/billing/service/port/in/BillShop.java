package org.cys.hexagonal.food.billing.service.port.in;

import org.cys.hexagonal.food.generic.money.domain.Money;

public interface BillShop {
    void bill(Long shopId, Money price);
}
