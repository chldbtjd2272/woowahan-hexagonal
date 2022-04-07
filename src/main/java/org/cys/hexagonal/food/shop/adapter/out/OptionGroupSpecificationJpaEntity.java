package org.cys.hexagonal.food.shop.adapter.out;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OPTION_GROUP_SPECS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionGroupSpecificationJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "OPTION_GROUP_SPEC_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EXCLUSIVE")
    private boolean exclusive;

    @Column(name = "BASIC")
    private boolean basic;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OPTION_GROUP_SPEC_ID")
    private List<OptionSpecificationJpaEntity> optionSpecs = new ArrayList<>();

    @Builder
    OptionGroupSpecificationJpaEntity(Long id,
                                      String name,
                                      boolean exclusive,
                                      boolean basic,
                                      List<OptionSpecificationJpaEntity> optionSpecs) {
        this.id = id;
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        this.optionSpecs = optionSpecs;
    }
}
