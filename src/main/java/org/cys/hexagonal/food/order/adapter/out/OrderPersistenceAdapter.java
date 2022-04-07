package org.cys.hexagonal.food.order.adapter.out;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.order.domain.Order;
import org.cys.hexagonal.food.order.service.port.out.CreateOrderPort;
import org.cys.hexagonal.food.order.service.port.out.LoadOrderPort;
import org.cys.hexagonal.food.order.service.port.out.UpdateOrderStatusPort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements CreateOrderPort, LoadOrderPort, UpdateOrderStatusPort {
    private final OrderRepository orderRepository;
    private final OrderJpaMapper orderJpaMapper;

    @Override
    public Order createOrder(Order order) {
        OrderJpaEntity orderJpaEntity = orderRepository.save(orderJpaMapper.mapFrom(order));
        return orderJpaMapper.mapFrom(orderJpaEntity);
    }

    @Override
    public Order findById(Long id) {
        return orderJpaMapper.mapFrom(
                orderRepository.findById(id).orElseThrow(IllegalArgumentException::new)
        );
    }

    @Override
    public Order updateOrderStatus(Order order) {
        if (order.getId() != null) {
            orderRepository.save(orderJpaMapper.mapFrom(order));
        } else {
            throw new IllegalStateException("not support update");
        }
        return order;
    }
}
