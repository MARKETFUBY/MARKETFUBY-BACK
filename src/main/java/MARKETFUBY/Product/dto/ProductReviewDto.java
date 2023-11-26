package MARKETFUBY.Product.dto;

import MARKETFUBY.Review.domain.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductReviewDto {
    private String name;
    private String level;
    private String brand;
    private String title;
    private String content;
    private List<String> imageList;
    private LocalDate date;
    private Boolean isReviewHelp;

    public static ProductReviewDto from(Review review, List<String> images, Boolean isReviewHelp){
        return ProductReviewDto.builder()
                .name(review.getWriter().getName())
                .level(review.getWriter().getLevel())
                .brand(review.getProduct().getBrand())
                .title(review.getProduct().getTitle())
                .content(review.getContent())
                .imageList(images)
                .date(review.getCreatedAt().toLocalDate())
                .isReviewHelp(isReviewHelp)
                .build();
    }
}
