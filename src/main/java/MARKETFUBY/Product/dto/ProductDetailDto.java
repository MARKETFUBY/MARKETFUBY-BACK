package MARKETFUBY.Product.dto;

import MARKETFUBY.Product.domain.Packing;
import MARKETFUBY.Product.domain.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDetailDto {
    private String image;
    private String title;
    private String subtitle;
    private Long price;
    private Long discount;
    private String brand;
    private Packing packing;
    private String unit;
    private String weight;
    private String origin;
    private String expiration;
    private String info;
    private String delivery;
    private String seller;
    private Boolean isLiked;
    private String detailInfoImg;
    private String productInfoImg;
    private List<String> reviewImages;
    private Integer reviewCount;
    private List<ProductReviewDto> reviews;
    private List<ProductInquiryDto> inquiries;

    @Builder
    public ProductDetailDto(Product product, List<String> reviewImages, Integer reviewCount, List<ProductReviewDto> reviews, List<ProductInquiryDto> inquiries){
        this.image = product.getImage();
        this.title = product.getTitle();
        this.subtitle = product.getSubtitle();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.brand = product.getBrand();
        this.packing = product.getPacking();
        this.unit = product.getUnit();
        this.weight = product.getWeight();
        this.origin = product.getOrigin();
        this.expiration = product.getExpiration();
        this.info = product.getInfo();
        this.delivery = product.getDelivery();
        this.seller = product.getSeller();
        this.detailInfoImg = product.getDetailInfoImg();
        this.productInfoImg = product.getProductInfoImg();
        this.reviewImages = reviewImages;
        this.reviewCount = reviewCount;
        this.reviews = reviews;
        this.inquiries = inquiries;
    }
}
