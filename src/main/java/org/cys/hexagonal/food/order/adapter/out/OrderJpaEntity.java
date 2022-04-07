package org.cys.hexagonal.food.order.adapter.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cys.hexagonal.food.order.domain.OrderStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@NoArgsConstructor
public class OrderJpaEntity {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private final List<OrderLineItemJpaEntity> orderLineItems = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "SHOP_ID")
    private Long shopId;
    @Column(name = "ORDERED_TIME")
    private LocalDateTime orderedTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private OrderStatus orderStatus;

    public OrderJpaEntity(Long id, Long userId, Long shopId, List<OrderLineItemJpaEntity> items, LocalDateTime orderedTime, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderedTime = orderedTime;
        this.orderStatus = status;
        this.orderLineItems.addAll(items);
    }
}
