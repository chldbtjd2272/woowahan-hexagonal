package org.cys.hexagonal.food.billing.adapter.out;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.billing.domain.Billing;
import org.cys.hexagonal.food.billing.service.port.out.BillingCommissionPort;
import org.cys.hexagonal.food.billing.service.port.out.LoadBillingPort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BillingPersistenceAdapter implements BillingCommissionPort, LoadBillingPort {
    private final BillingJpaMapper billingJpaMapper;
    private final BillingRepository billingRepository;


    @Override
    public Billing bill(Billing billing) {
        if (billing.getId() != null) {
            billingRepository.save(billingJpaMapper.mapFrom(billing));
        } else {
            throw new IllegalStateException("not support update");
        }
        return billing;
    }

    @Override
    public Billing loadByShopId(Long shopId) {
        return billingJpaMapper.mapFrom(billingRepository.findByShopId(shopId).orElseThrow(IllegalArgumentException::new));
    }
}
