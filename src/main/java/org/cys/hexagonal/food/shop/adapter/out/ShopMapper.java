package org.cys.hexagonal.food.shop.adapter.out;

import org.cys.hexagonal.food.shop.domain.Menu;
import org.cys.hexagonal.food.shop.domain.OptionGroupSpecification;
import org.cys.hexagonal.food.shop.domain.OptionSpecification;
import org.cys.hexagonal.food.shop.domain.Shop;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShopMapper {

    Shop mapToShop(ShopJpaEntity entity) {
        return Shop.builder()
                .id(entity.getId())
                .commissionRate(entity.getCommissionRate())
                .minOrderAmount(entity.getMinOrderAmount())
                .name(entity.getName())
                .open(entity.isOpen())
                .build();
    }

    ShopJpaEntity mapToShopJpaEntity(Shop shop) {
        return ShopJpaEntity.builder()
                .commissionRate(shop.getCommissionRate())
                .minOrderAmount(shop.getMinOrderAmount())
                .name(shop.getName())
                .open(shop.isOpen())
                .build();
    }

    Menu mapToMenu(MenuJpaEntity entity) {
        return Menu.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .shopId(entity.getShopId())
                .additives(mapToGroup(entity.getOptionGroupSpecs()))
                .build();
    }

    private List<OptionGroupSpecification> mapToGroup(List<OptionGroupSpecificationJpaEntity> entity) {
        if (Hibernate.isInitialized(entity)) {
            return entity.stream()
                    .map(group -> OptionGroupSpecification.builder()
                            .id(group.getId())
                            .name(group.getName())
                            .exclusive(group.isExclusive())
                            .basic(group.isBasic())
                            .options(mapToSpec(group.getOptionSpecs()))
                            .build()).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private List<OptionSpecification> mapToSpec(List<OptionSpecificationJpaEntity> entity) {
        if (Hibernate.isInitialized(entity)) {
            return entity.stream()
                    .map(spec -> OptionSpecification.builder()
                            .id(spec.getId())
                            .name(spec.getName())
                            .price(spec.getPrice())
                            .build())
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    MenuJpaEntity mapToMenuJpaEntity(Menu menu) {
        List<OptionGroupSpecificationJpaEntity> optionGroupSpecs = menu.getOptionGroupSpecs().stream()
                .map(group -> {
                    List<OptionSpecificationJpaEntity> optionSpecs = group.getOptionSpecs().stream()
                            .map(spec -> OptionSpecificationJpaEntity.builder()
                                    .id(spec.getId())
                                    .name(spec.getName())
                                    .price(spec.getPrice())
                                    .build())
                            .collect(Collectors.toList());
                    return OptionGroupSpecificationJpaEntity.builder()
                            .id(group.getId())
                            .name(group.getName())
                            .exclusive(group.isExclusive())
                            .basic(group.isBasic())
                            .optionSpecs(optionSpecs)
                            .build();
                }).collect(Collectors.toList());

        return MenuJpaEntity.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .shopId(menu.getShopId())
                .optionGroupSpecs(optionGroupSpecs)
                .build();
    }
}
