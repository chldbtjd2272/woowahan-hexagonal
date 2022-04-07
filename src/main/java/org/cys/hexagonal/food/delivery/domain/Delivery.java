package org.cys.hexagonal.food.delivery.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Delivery {

    private Long id;
    private Long orderId;
    private DeliveryStatus deliveryStatus;

    public Delivery(Long orderId, DeliveryStatus deliveryStatus) {
        this(null, orderId, deliveryStatus);
    }

    @Builder
    public Delivery(Long id, Long orderId, DeliveryStatus deliveryStatus) {
        this.id = id;
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
    }

    public static Delivery started(Long orderId) {
        return new Delivery(orderId, DeliveryStatus.DELIVERING);
    }

    public void complete() {
        this.deliveryStatus = DeliveryStatus.DELIVERED;
    }
}
