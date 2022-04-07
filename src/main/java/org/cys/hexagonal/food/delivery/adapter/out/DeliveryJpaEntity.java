package org.cys.hexagonal.food.delivery.adapter.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cys.hexagonal.food.delivery.domain.DeliveryStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DELIVERIES")
@Getter
@NoArgsConstructor
public class DeliveryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private DeliveryStatus deliveryStatus;

    public DeliveryJpaEntity(Long id, Long orderId, DeliveryStatus deliveryStatus) {
        this.id = id;
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
    }

}
