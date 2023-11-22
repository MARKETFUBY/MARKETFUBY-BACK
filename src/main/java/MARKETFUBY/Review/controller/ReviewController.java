package MARKETFUBY.Review.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MARKETFUBY.Review.dto.ReviewResponseDto;
import MARKETFUBY.Review.dto.ReviewRequestDto;
import MARKETFUBY.Review.service.ReviewService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@GetMapping(value="mypage/review")
	@ResponseStatus(value= HttpStatus.OK)
	public ReviewResponseDto getReviewList(){
		return reviewService.getReviewList();
	}
	@PostMapping(value="/mypage/review")
	@ResponseStatus(value= HttpStatus.OK)
	public String postReview(@RequestBody ReviewRequestDto reviewRequestDto){
		reviewService.create(reviewRequestDto);
		return "문의가 정상적으로 등록되었습니다.";
	}

	@PutMapping("/mypage/review/{reviewId}")
	@ResponseStatus(value = HttpStatus.OK)
	public String updateInquiry(@PathVariable Long reviewId, @RequestBody ReviewRequestDto reviewRequestDto){
		reviewService.update(reviewId, reviewRequestDto);
		return "문의가 정상적으로 수정되었습니다.";
	}
}
