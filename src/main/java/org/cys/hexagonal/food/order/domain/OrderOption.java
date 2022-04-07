package org.cys.hexagonal.food.order.domain;

import lombok.Builder;
import lombok.Getter;
import org.cys.hexagonal.food.generic.money.domain.Money;
import org.cys.hexagonal.food.shop.domain.Option;

@Getter
public class OrderOption {

    private String name;
    private Money price;

    @Builder
    public OrderOption(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public Option convertToOption() {
        return new Option(name, price);
    }
}
