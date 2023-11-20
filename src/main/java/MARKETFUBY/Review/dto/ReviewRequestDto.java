package MARKETFUBY.Review.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class ReviewRequestDto {
	private Long productId;
	private String content;
	private List<String> imageUrl;
}
