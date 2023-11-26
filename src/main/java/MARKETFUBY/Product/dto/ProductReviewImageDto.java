package MARKETFUBY.Product.dto;

import MARKETFUBY.ReviewImage.domain.ReviewImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductReviewImageDto {
    private String imgUrl;

    public static ProductReviewImageDto from(ReviewImage reviewImage){
        return ProductReviewImageDto.builder()
                .imgUrl(reviewImage.getUrl())
                .build();
    }
}
