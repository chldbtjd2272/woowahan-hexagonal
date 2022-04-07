package org.cys.hexagonal.food.shop.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class OptionGroupSpecification {
    private Long id;
    private String name;
    private boolean exclusive;
    private boolean basic;
    private List<OptionSpecification> optionSpecs = new ArrayList<>();

    @Builder
    public OptionGroupSpecification(Long id, String name, boolean exclusive, boolean basic, List<OptionSpecification> options) {
        this.id = id;
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        this.optionSpecs.addAll(options);
    }

    public OptionGroupSpecification(String name, boolean exclusive, boolean basic, OptionSpecification... options) {
        this(null, name, exclusive, basic, Arrays.asList(options));
    }

    public static OptionGroupSpecification basic(String name, boolean exclusive, OptionSpecification... options) {
        return new OptionGroupSpecification(name, exclusive, true, options);
    }

    public static OptionGroupSpecification additive(String name, boolean exclusive, OptionSpecification... options) {
        return new OptionGroupSpecification(name, exclusive, false, options);
    }

    public String getName() {
        return name;
    }

    public boolean isSatisfiedBy(OptionGroup optionGroup) {
        return !isSatisfied(optionGroup.getName(), satisfied(optionGroup.getOptions()));
    }

    private boolean isSatisfied(String groupName, List<Option> satisfied) {
        if (!name.equals(groupName)) {
            return false;
        }

        if (satisfied.isEmpty()) {
            return false;
        }

        if (exclusive && satisfied.size() > 1) {
            return false;
        }

        return true;
    }

    private List<Option> satisfied(List<Option> options) {
        return optionSpecs
                .stream()
                .flatMap(spec -> options.stream().filter(spec::isSatisfiedBy))
                .collect(toList());
    }
}
