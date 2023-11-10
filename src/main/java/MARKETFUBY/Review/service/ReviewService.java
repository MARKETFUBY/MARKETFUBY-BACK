package MARKETFUBY.Review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import MARKETFUBY.Inquiry.domain.Inquiry;
import MARKETFUBY.Inquiry.dto.InquiryRequestDto;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.repository.ProductRepository;
import MARKETFUBY.Review.domain.Review;
import MARKETFUBY.Review.dto.ReviewRequestDto;
import MARKETFUBY.Review.repository.ReviewRepository;
import MARKETFUBY.ReviewImage.domain.ReviewImage;
import MARKETFUBY.ReviewImage.repository.ReviewImageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ProductRepository productRepository;
	private final ReviewRepository reviewRepository;
	private final ReviewImageRepository reviewImageRepository;

	public void create(ReviewRequestDto reviewRequestDto){
		//현재 로그인 중인 사용자 불러오는 단계 필요
		Long memberId=1L;
		Member member=new Member();
		member.setMemberId(1L);
		Long productId= reviewRequestDto.getProductId();
		Product product=productRepository.findById(productId)
			.orElseThrow(()->new IllegalArgumentException("존재하지 않는 제품입니다."));
		Review review=Review.builder()
			.writer(member)
			.content(reviewRequestDto.getContent())
			.product(product)
			.build();
		reviewRepository.save(review);
		//Long id=review.getReviewId();
		List<String> imageList=reviewRequestDto.getImageUrl();
		for(String url: imageList){
			ReviewImage reviewImage=ReviewImage.builder()
				.review(review)
				.url(url)
				.build();
			reviewImageRepository.save(reviewImage);
		}

	}

	public void update(Long reviewId, ReviewRequestDto reviewRequestDto){
		Review review=reviewRepository.findById(reviewId)
			.orElseThrow(()->new IllegalArgumentException("존재하지 않는 reviewId입니다."));
		review.updateReview(reviewRequestDto.getContent());
		reviewRepository.save(review);
	}
}
