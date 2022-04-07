package org.cys.hexagonal.food.billing.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingRepository extends JpaRepository<BillingJpaEntity, Long> {
    Optional<BillingJpaEntity> findByShopId(Long shopId);
}
