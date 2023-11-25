package MARKETFUBY.Product.dto;

import MARKETFUBY.Review.domain.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductReviewDto {
    private String name;
    private String level;
    private String brand;
    private String title;
    private String content;
    // private String imgUrl;
    private LocalDate date;

    public static ProductReviewDto from(Review review){
        return ProductReviewDto.builder()
                .name(review.getWriter().getName())
                .level(review.getWriter().getLevel())
                .brand(review.getProduct().getBrand())
                .title(review.getProduct().getTitle())
                .content(review.getContent())
                .date(review.getCreatedAt().toLocalDate())
                .build();
    }
}
