package MARKETFUBY.Product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HelpReviewListResponseDto {
    private Integer helpReviewsNum;
    private List<HelpReviewDto> helpReviews;

    @Builder
    public HelpReviewListResponseDto(Integer helpReviewsNum, List<HelpReviewDto> helpReviews){
        this.helpReviewsNum = helpReviewsNum;
        this.helpReviews = helpReviews;
    }
}
