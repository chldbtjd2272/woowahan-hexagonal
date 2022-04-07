package org.cys.hexagonal.food.billing.service.port.out;

import org.cys.hexagonal.food.billing.domain.Billing;

public interface LoadBillingPort {
    Billing loadByShopId(Long shopId);
}
