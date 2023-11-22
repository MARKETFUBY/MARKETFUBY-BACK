package MARKETFUBY.Review.dto;

import java.time.LocalDate;
import java.util.Date;

import MARKETFUBY.Inquiry.domain.Inquiry;
import MARKETFUBY.Review.domain.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {
	//image, title, brand, date, status
	private String imageUrl;
	private String title;
	private String brand;
	private LocalDate date;
	private String status;

	public ReviewDto(Review review){
		this.imageUrl=review.getProduct().getImage();
		this.title=review.getProduct().getTitle();
		this.brand=review.getProduct().getBrand();
		this.date=review.getCreatedAt().toLocalDate();
		this.status="배송완료";
	}
}
