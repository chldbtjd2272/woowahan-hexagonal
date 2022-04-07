package org.cys.hexagonal.food.order.adapter.out;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ORDER_OPTION_GROUPS")
@Getter
@NoArgsConstructor
public class OrderOptionGroupJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_OPTION_GROUP_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ElementCollection
    @CollectionTable(name = "ORDER_OPTIONS", joinColumns = @JoinColumn(name = "ORDER_OPTION_GROUP_ID"))
    private List<OrderOptionJpaEmbeddable> orderOptions;

    public OrderOptionGroupJpaEntity(Long id, String name, List<OrderOptionJpaEmbeddable> options) {
        this.id = id;
        this.name = name;
        this.orderOptions = options;
    }
}
