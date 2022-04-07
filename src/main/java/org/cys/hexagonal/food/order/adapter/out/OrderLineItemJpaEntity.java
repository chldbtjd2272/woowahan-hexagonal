package org.cys.hexagonal.food.order.adapter.out;

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
@Table(name = "ORDER_LINE_ITEMS")
@Getter
@NoArgsConstructor
public class OrderLineItemJpaEntity {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_LINE_ITEM_ID")
    private final List<OrderOptionGroupJpaEntity> groups = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_LINE_ITEM_ID")
    private Long id;
    @Column(name = "MENU_ID")
    private Long menuId;
    @Column(name = "FOOD_NAME")
    private String name;
    @Column(name = "FOOD_COUNT")
    private int count;

    public OrderLineItemJpaEntity(Long id, Long menuId, String name, int count, List<OrderOptionGroupJpaEntity> groups) {
        this.id = id;
        this.menuId = menuId;
        this.name = name;
        this.count = count;
        this.groups.addAll(groups);
    }
}
