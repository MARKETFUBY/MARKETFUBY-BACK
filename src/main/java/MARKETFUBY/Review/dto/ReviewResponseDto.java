package MARKETFUBY.Review.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {
	private String name;
	private String level;
	private Integer total;
	private List<ReviewDto> productList;

}
