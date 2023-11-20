package MARKETFUBY.CartProduct.domain;

import MARKETFUBY.Cart.domain.Cart;
import MARKETFUBY.Product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "cartproduct")
@NoArgsConstructor
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartproduct_id", nullable = false, updatable = false)
    private Long cartProductId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column(nullable = false)
    private Long count;

    @Builder
    public CartProduct(Product product, Cart cart, Long count){
        this.product = product;
        this.cart = cart;
        this.count = count;
    }
    public void updateCartProduct(Long count){
        this.count=count;
    }
}
