package MARKETFUBY.OrderProduct.domain;

import MARKETFUBY.Order.domain.Order;
import MARKETFUBY.Product.domain.Product;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orderproduct")
@NoArgsConstructor
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderproduct_id", nullable = false, updatable = false)
    private Long orderProductId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Long count;

    @Column(nullable = false)
    private Long price;

    @Builder
    public OrderProduct(Product product, Order order, Long count, Long price){
        this.product = product;
        this.order = order;
        this.count = count;
        this.price = price;
    }
}
