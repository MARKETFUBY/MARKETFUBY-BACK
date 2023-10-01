package MARKETFUBY.Cart.domain;

import MARKETFUBY.CartProduct.domain.CartProduct;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.OrderProduct.domain.OrderProduct;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
    @Table(name="cart")
    @NoArgsConstructor
    public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", updatable = false)
    private Long cartId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<CartProduct> cartList = new ArrayList<>();

    @Builder
    public Cart(Member member, List<CartProduct> cartList){
        this.member = member;
        this.cartList = cartList;
    }
}
