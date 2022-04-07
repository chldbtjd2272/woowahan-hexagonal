package org.cys.hexagonal.food.delivery.adapter.out;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.delivery.domain.Delivery;
import org.cys.hexagonal.food.delivery.service.port.out.CreateDeliveryPort;
import org.cys.hexagonal.food.delivery.service.port.out.LoadDeliveryPort;
import org.cys.hexagonal.food.delivery.service.port.out.UpdateDeliveryStatusPort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryPersistenceAdapter implements CreateDeliveryPort, LoadDeliveryPort, UpdateDeliveryStatusPort {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryJpaMapper deliveryJpaMapper;

    @Override
    public Delivery createDelivery(Delivery delivery) {
        DeliveryJpaEntity deliveryJpaEntity = deliveryRepository.save(deliveryJpaMapper.mapFrom(delivery));
        return deliveryJpaMapper.mapFrom(deliveryJpaEntity);
    }

    @Override
    public Delivery loadDeliveryById(Long id) {
        return deliveryJpaMapper.mapFrom(deliveryRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public Delivery updateDeliveryStatus(Delivery delivery) {
        if (delivery.getId() != null) {
            deliveryRepository.save(deliveryJpaMapper.mapFrom(delivery));
        } else {
            throw new IllegalStateException("not support update");
        }
        return delivery;
    }
}
