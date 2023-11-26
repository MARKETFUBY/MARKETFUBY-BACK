package MARKETFUBY.Product.dto;

import MARKETFUBY.Product.domain.Product;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeProductDto {
    private String image;
    private String brand;
    private String title;
    private Long price;
    private Long discount;

    public static LikeProductDto from(Product product){
        return LikeProductDto.builder()
                .image(product.getImage())
                .brand(product.getBrand())
                .title(product.getTitle())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .build();
    }
}
