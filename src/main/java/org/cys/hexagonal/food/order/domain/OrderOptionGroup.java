package org.cys.hexagonal.food.order.domain;

import lombok.Builder;
import lombok.Getter;
import org.cys.hexagonal.food.generic.money.domain.Money;
import org.cys.hexagonal.food.shop.domain.OptionGroup;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class OrderOptionGroup {

    private Long id;

    private String name;

    private List<OrderOption> orderOptions;

    public OrderOptionGroup(String name, List<OrderOption> options) {
        this(null, name, options);
    }

    @Builder
    public OrderOptionGroup(Long id, String name, List<OrderOption> options) {
        this.id = id;
        this.name = name;
        this.orderOptions = options;
    }

    public Money calculatePrice() {
        return Money.sum(orderOptions, OrderOption::getPrice);
    }

    public OptionGroup convertToOptionGroup() {
        return new OptionGroup(name, orderOptions.stream().map(OrderOption::convertToOption).collect(toList()));
    }
}
