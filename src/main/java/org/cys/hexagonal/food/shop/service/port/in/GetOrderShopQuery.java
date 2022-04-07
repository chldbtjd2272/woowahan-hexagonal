package org.cys.hexagonal.food.shop.service.port.in;

import org.cys.hexagonal.food.shop.domain.Menu;
import org.cys.hexagonal.food.shop.domain.Shop;

import java.util.List;

public interface GetOrderShopQuery {
    Shop findShopById(Long shopId);

    List<Menu> findMenusByMenuIds(List<Long> menuIds);
}
