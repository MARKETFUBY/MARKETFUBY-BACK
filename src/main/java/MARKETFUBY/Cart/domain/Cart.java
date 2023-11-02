package MARKETFUBY.Cart.domain;

import MARKETFUBY.CartProduct.domain.CartProduct;
import MARKETFUBY.Member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
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

    @OneToMany(mappedBy = "cart")
    private List<CartProduct> cartList = new ArrayList<>();

    @Builder
    public Cart(Member member, List<CartProduct> cartList){
        this.member = member;
        this.cartList = cartList;
    }

    @Builder
    public Cart(Member member){
        this.member=member;
    }

}
