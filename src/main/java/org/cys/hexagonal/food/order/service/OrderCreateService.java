package org.cys.hexagonal.food.order.service;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.order.domain.Order;
import org.cys.hexagonal.food.order.service.port.in.PlaceOrder;
import org.cys.hexagonal.food.order.service.port.out.CreateOrderPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderCreateService implements PlaceOrder {
    private final CreateOrderPort createOrderPort;
    private final OrderValidator orderValidator;
    private final OrderMapper orderMapper;


    @Transactional
    public void placeOrder(Cart cart) {
        Order order = orderMapper.mapFrom(cart);
        order.place(orderValidator);
        createOrderPort.createOrder(order);
    }
}
