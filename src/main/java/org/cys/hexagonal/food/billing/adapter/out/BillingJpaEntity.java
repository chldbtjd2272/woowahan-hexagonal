package org.cys.hexagonal.food.billing.adapter.out;

import lombok.Getter;
import org.cys.hexagonal.food.generic.money.domain.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "BILLINGS")
@Getter
public class BillingJpaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "BILLING_ID")
    private Long id;

    @Column(name = "SHOP_ID")
    private Long shopId;

    @Column(name = "COMMISSION")
    private Money commission = Money.ZERO;

    public BillingJpaEntity(Long id, Long shopId, Money commission) {
        this.id = id;
        this.shopId = shopId;
        this.commission = commission;
    }
}
