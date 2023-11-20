package MARKETFUBY.Product.domain;

import MARKETFUBY.BigCategory.domain.BigCategory;
import MARKETFUBY.Event.domain.Event;
import MARKETFUBY.SmallCategory.domain.SmallCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", updatable = false)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "bigcategory_id", nullable = false)
    private BigCategory bigCategory;

    @ManyToOne
    @JoinColumn(name = "smallcategory_id", nullable = false)
    private SmallCategory smallCategory;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String title;
    @Column
    private String subtitle;
    @Column(nullable = false)
    private Long price;
    @Column
    private Long discount;
    @Column(nullable = false)
    private String brand;
    @Column
    private Packing packing;
    @Column
    private String unit;
    @Column
    private String weight;
    @Column
    private String origin;
    @Column
    private String expiration;
    @Column
    private String info;
    @Column
    private String delivery;
    @Column
    private String seller;
    @Column
    private String date;

    @Builder
    public Product(BigCategory bigCategory, SmallCategory smallCategory, Event event, String image, String  title, String  subtitle, Long price, Long discount, String brand, Packing packing, String unit, String weight, String origin, String expiration, String info, String delivery, String seller, String date){
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.event = event;
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.discount = discount;
        this.brand = brand;
        this.packing = packing;
        this.unit = unit;
        this.weight = weight;
        this.origin = origin;
        this.expiration = expiration;
        this.info = info;
        this.delivery = delivery;
        this.seller = seller;
        this.date = date;
    }
}
