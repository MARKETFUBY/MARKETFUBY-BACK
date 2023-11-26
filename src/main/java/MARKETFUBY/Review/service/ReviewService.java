package MARKETFUBY.Review.service;

import java.util.ArrayList;
import java.util.List;

import MARKETFUBY.Product.dto.HelpReviewDto;
import MARKETFUBY.Product.dto.HelpReviewListResponseDto;
import MARKETFUBY.ReviewHelp.domain.ReviewHelp;
import MARKETFUBY.ReviewHelp.repository.ReviewHelpRepository;
import org.springframework.stereotype.Service;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Member.service.MemberService;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.repository.ProductRepository;
import MARKETFUBY.Review.domain.Review;
import MARKETFUBY.Review.dto.ReviewDto;
import MARKETFUBY.Review.dto.ReviewResponseDto;
import MARKETFUBY.Review.dto.ReviewRequestDto;
import MARKETFUBY.Review.repository.ReviewRepository;
import MARKETFUBY.ReviewImage.domain.ReviewImage;
import MARKETFUBY.ReviewImage.repository.ReviewImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ProductRepository productRepository;
	private final ReviewRepository reviewRepository;
	private final ReviewImageRepository reviewImageRepository;
	private final MemberService memberService;
	private final ReviewHelpRepository reviewHelpRepository;

	public ReviewResponseDto getReviewList(){
		ReviewResponseDto reviewResponseDto=new ReviewResponseDto();

		//현재 로그인 중인 사용자 불러오기
		Member member= memberService.getCurrentMember();

		reviewResponseDto.setName(member.getName());
		reviewResponseDto.setLevel(member.getLevel());

		List<Review> reviewList=reviewRepository.findAllByWriter(member);
		List<ReviewDto> reviewDtoList=new ArrayList<>();
		for(Review review:reviewList){
			reviewDtoList.add(new ReviewDto(review));
		}
		reviewResponseDto.setTotal(reviewDtoList.size());
		reviewResponseDto.setProductList(reviewDtoList);
		return reviewResponseDto;
	}

	public void create(ReviewRequestDto reviewRequestDto){

		//현재 로그인 중인 사용자 불러오기
		Member member= memberService.getCurrentMember();
		System.out.println("memberName : "+member.getName());

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

	public HelpReviewListResponseDto getHelpReviewListDto(Member member){
		List<HelpReviewDto> helpReviewDtos = findHelpReviewsByMember(member);
		Integer helpReviewsNum = helpReviewDtos.size();
		return new HelpReviewListResponseDto(helpReviewsNum, helpReviewDtos);
	}
	public List<HelpReviewDto> findHelpReviewsByMember(Member member){
		List<ReviewHelp> reviewHelpList = reviewHelpRepository.findAllByMember(member);
		List<Review> helpReviews = new ArrayList<>();
		List<HelpReviewDto> helpReviewDtos = new ArrayList<>();
		reviewHelpList.forEach(reviewHelp -> {
			Review review = reviewHelp.getReview();
			helpReviews.add(review);
		});
		helpReviews.forEach(review -> {
			HelpReviewDto helpReviewDto = HelpReviewDto.from(review);
			helpReviewDtos.add(helpReviewDto);
		});
		return helpReviewDtos;
	}

	// 도움돼요에서 사용
	@Transactional(readOnly = true)
	public Review findReviewById(Long reviewId){
		return reviewRepository.findById(reviewId)
				.orElseThrow(()->new EntityNotFoundException("해당 리뷰가 존재하지 않습니다."));
	}
}
