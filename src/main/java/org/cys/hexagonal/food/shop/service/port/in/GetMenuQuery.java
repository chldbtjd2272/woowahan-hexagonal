package org.cys.hexagonal.food.shop.service.port.in;

import org.cys.hexagonal.food.shop.service.MenuBoard;

public interface GetMenuQuery {
    MenuBoard getMenuBoard(Long shopId);
}
