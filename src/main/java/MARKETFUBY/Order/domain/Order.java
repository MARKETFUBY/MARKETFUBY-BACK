package MARKETFUBY.Order.domain;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.OrderProduct.domain.OrderProduct;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="order")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false)
    private Long orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member orderer;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderList = new ArrayList<>();

    @Builder
    public Order(Member orderer, List<OrderProduct> orderList){
        this.orderer = orderer;
        this.orderList = orderList;
    }
}
