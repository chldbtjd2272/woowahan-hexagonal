package org.cys.hexagonal.food.billing.service;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.billing.domain.Billing;
import org.cys.hexagonal.food.billing.service.port.in.BillShop;
import org.cys.hexagonal.food.billing.service.port.out.BillingCommissionPort;
import org.cys.hexagonal.food.billing.service.port.out.LoadBillingPort;
import org.cys.hexagonal.food.generic.money.domain.Money;
import org.cys.hexagonal.food.shop.domain.Shop;
import org.cys.hexagonal.food.shop.service.port.out.LoadShopPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BillShopService implements BillShop {
    private final LoadShopPort loadShopPort;
    private final LoadBillingPort loadBillingPort;
    private final BillingCommissionPort billingCommissionPort;

    @Override
    @Transactional
    public void bill(Long shopId, Money price) {
        Shop shop = loadShopPort.findShopById(shopId);
        Billing billing = loadBillingPort.loadByShopId(shopId);

        billing.billCommissionFee(shop.calculateCommissionFee(price));
        billingCommissionPort.bill(billing);
    }
}
