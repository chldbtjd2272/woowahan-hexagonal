package org.cys.hexagonal.food.shop.service;

import lombok.RequiredArgsConstructor;
import org.cys.hexagonal.food.shop.domain.Menu;
import org.cys.hexagonal.food.shop.domain.Shop;
import org.cys.hexagonal.food.shop.service.port.in.GetOrderShopQuery;
import org.cys.hexagonal.food.shop.service.port.out.LoadShopPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderShopQueryService implements GetOrderShopQuery {
    private final LoadShopPort loadShopPort;

    @Override
    public Shop findShopById(Long shopId) {
        return loadShopPort.findShopById(shopId);
    }

    @Override
    public List<Menu> findMenusByMenuIds(List<Long> menuIds) {
        return loadShopPort.findMenusByMenuIds(menuIds);
    }
}
