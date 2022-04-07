package org.cys.hexagonal.food.order.service.port.in;

import org.cys.hexagonal.food.order.service.Cart;

public interface PlaceOrder {
    void placeOrder(Cart cart);
}
