package org.cys.hexagonal.food.shop.domain;

import lombok.Builder;
import lombok.Getter;
import org.cys.hexagonal.food.generic.money.domain.Money;

import java.util.Objects;


@Getter
public class OptionSpecification {
    private Long id;
    private String name;
    private Money price;

    public OptionSpecification(String name, Money price) {
        this(null, name, price);
    }

    @Builder
    public OptionSpecification(Long id, String name, Money price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof OptionSpecification)) {
            return false;
        }

        OptionSpecification other = (OptionSpecification) object;
        return Objects.equals(name, other.getName()) && Objects.equals(price, other.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    public boolean isSatisfiedBy(Option option) {
        return Objects.equals(name, option.getName()) && Objects.equals(price, option.getPrice());
    }
}
