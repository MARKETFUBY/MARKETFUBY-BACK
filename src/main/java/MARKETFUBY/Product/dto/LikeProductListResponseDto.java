package MARKETFUBY.Product.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class LikeProductListResponseDto {
    private Integer likeProductNum;
    private List<LikeProductDto> likeProducts;

    @Builder
    public LikeProductListResponseDto(Integer likeProductNum, List<LikeProductDto> likeProducts){
        this.likeProductNum = likeProductNum;
        this.likeProducts= likeProducts;
    }
}
