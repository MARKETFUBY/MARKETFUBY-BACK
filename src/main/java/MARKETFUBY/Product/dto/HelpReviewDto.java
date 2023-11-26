package MARKETFUBY.Product.dto;

import MARKETFUBY.Review.domain.Review;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HelpReviewDto {
    private String name;
    private String level;
    private String brand;
    private String title;
    private String content;
    private LocalDate date;

    public static HelpReviewDto from(Review review){
        return HelpReviewDto.builder()
                .name(review.getWriter().getName())
                .level(review.getWriter().getLevel())
                .brand(review.getProduct().getTitle())
                .content(review.getContent())
                .date(review.getCreatedAt().toLocalDate())
                .build();
    }
}
