package org.cys.hexagonal.food.delivery.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryJpaEntity, Long> {
}
