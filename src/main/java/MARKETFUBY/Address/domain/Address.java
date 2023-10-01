package MARKETFUBY.Address.domain;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Order.domain.Order;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", updatable = false)
    private Long addressId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member orderer;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column
    private String address;
    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    @Builder
    public Address(Member orderer, Order order, String address, boolean isDefault){
        this.orderer = orderer;
        this.order = order;
        this.address = address;
        this.isDefault = isDefault;
    }
}
