package org.cys.hexagonal.food.shop.adapter.out;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MENUS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuJpaEntity {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "MENU_ID")
    private final List<OptionGroupSpecificationJpaEntity> optionGroupSpecs = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;
    @Column(name = "FOOD_NAME")
    private String name;
    @Column(name = "FOOD_DESCRIPTION")
    private String description;
    @Column(name = "SHOP_ID")
    private Long shopId;

    @Builder
    public MenuJpaEntity(Long id, String name, String description, Long shopId, List<OptionGroupSpecificationJpaEntity> optionGroupSpecs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shopId = shopId;
        this.optionGroupSpecs.addAll(optionGroupSpecs);
    }
}
