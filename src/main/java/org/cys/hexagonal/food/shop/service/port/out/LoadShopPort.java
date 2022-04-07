package org.cys.hexagonal.food.shop.service.port.out;

import org.cys.hexagonal.food.shop.domain.Menu;
import org.cys.hexagonal.food.shop.domain.Shop;

import java.util.List;

public interface LoadShopPort {
    Shop findShopById(Long id);

    List<Menu> findMenusByShopId(Long shopId);

    List<Menu> findMenusByMenuIds(List<Long> menuIds);

}
