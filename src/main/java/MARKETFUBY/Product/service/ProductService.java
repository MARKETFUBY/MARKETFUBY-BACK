package MARKETFUBY.Product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import MARKETFUBY.Inquiry.domain.Inquiry;
import MARKETFUBY.Inquiry.repository.InquiryRepository;
import MARKETFUBY.Like.repository.LikeRepository;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Member.repository.MemberRepository;
import MARKETFUBY.Product.dto.*;
import MARKETFUBY.Review.domain.Review;
import MARKETFUBY.Review.dto.ReviewResponseDto;
import MARKETFUBY.ReviewHelp.repository.ReviewHelpRepository;
import MARKETFUBY.ReviewImage.domain.ReviewImage;
import MARKETFUBY.ReviewImage.repository.ReviewImageRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;

import MARKETFUBY.BigCategory.domain.BigCategory;
import MARKETFUBY.BigCategory.dto.CategoryDto;
import MARKETFUBY.BigCategory.repository.BigCategoryRepository;
import MARKETFUBY.Event.domain.Event;
import MARKETFUBY.Event.repository.EventRepository;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.repository.ProductRepository;
import MARKETFUBY.Review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final EventRepository eventRepository;
	private final BigCategoryRepository bigcategoryRepository;
	private final ReviewRepository reviewRepository;
	private final InquiryRepository inquiryRepository;
	private final ReviewHelpRepository reviewHelpRepository;
	private final MemberRepository memberRepository;
	private final ReviewImageRepository reviewImageRepository;
	private final LikeRepository likeRepository;

	public MainDto getMainList(){
		MainDto mainDto=new MainDto();
		List<Product> productList1= new ArrayList<>();
		List<Product> productList2= new ArrayList<>();
		List<Product> productList3= new ArrayList<>();

		Event event1=eventRepository.findById(4L).orElseThrow(()->new IllegalArgumentException("존재하지 않는 이벤트입니다."));
		productList1=productRepository.findTop12ByEvent(event1);

		List<ProductDto> productDtoList1=new ArrayList<>();
		for(Product product:productList1){
			ProductDto productDto=new ProductDto(product);
			productDto.setReviewNum(reviewRepository.countByProduct(product));
			productDtoList1.add(productDto);
		}

		Event event2=eventRepository.findById(5L).orElseThrow(()->new IllegalArgumentException("존재하지 않는 이벤트입니다."));
		productList2=productRepository.findTop12ByEvent(event2);

		List<ProductDto> productDtoList2=new ArrayList<>();
		for(Product product:productList2){
			ProductDto productDto=new ProductDto(product);
			productDto.setReviewNum(reviewRepository.countByProduct(product));
			productDtoList2.add(productDto);
		}

		Event event3=eventRepository.findById(6L).orElseThrow(()->new IllegalArgumentException("존재하지 않는 이벤트입니다."));
		productList3=productRepository.findTop12ByEvent(event3);

		List<ProductDto> productDtoList3=new ArrayList<>();
		for(Product product:productList3){
			ProductDto productDto=new ProductDto(product);
			productDto.setReviewNum(reviewRepository.countByProduct(product));
			productDtoList3.add(productDto);
		}
		
		//임시 테스트용
		mainDto.setEvent1(productDtoList1);
		mainDto.setEvent2(productDtoList2);
		mainDto.setEvent3(productDtoList3);
		return mainDto;

	}

	public ProductsListDto getProductList(Integer sort, Long filters, Long eventId){

		ProductsListDto newProductsDto=new ProductsListDto();
		List<Product> productList= new ArrayList<>();

		//신상품(eventId=1), 베스트(eventId=2), 알뜰쇼핑(eventId=3)으로 들어온 경로
		Event event=eventRepository.findById(eventId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 이벤트입니다."));

		//filters 선택 안한 경우
		if(filters==0L){
			if(sort==5) {//낮은가격순
				productList=productRepository.findTop12ByEventOrderByPriceAsc(event);
			} else if (sort==4) {//높은가격순
				productList=productRepository.findTop12ByEventOrderByPriceDesc(event);
			} else if(sort==3){//혜택순
				productList=productRepository.findTop12ByEventOrderByDiscountDesc(event);
			} else{//나머지
				productList=productRepository.findTop12ByEvent(event);
			}
		}
		else{ //filters 선택한 경우
			BigCategory bigCategory=bigcategoryRepository.findById(filters).orElseThrow(()->new IllegalArgumentException("존재하지 않는 대카테고리입니다."));
			if(sort==5) {//낮은가격순
				productList=productRepository.findTop12ByEventAndBigCategoryOrderByPriceAsc(event, bigCategory);
			} else if (sort==4) {//높은가격순
				productList=productRepository.findTop12ByEventAndBigCategoryOrderByPriceDesc(event, bigCategory);
			} else if(sort==3){//혜택순
				productList=productRepository.findTop12ByEventAndBigCategoryOrderByDiscountDesc(event, bigCategory);
			} else{//나머지
				productList=productRepository.findTop12ByEventAndBigCategory(event, bigCategory);
			}
		}

		List<ProductDto> productDtoList=new ArrayList<>();
		for(Product product:productList){
			ProductDto productDto=new ProductDto(product);
			productDto.setReviewNum(reviewRepository.countByProduct(product));
			productDtoList.add(productDto);
		}

		newProductsDto.setProductList(productDtoList);
		List<CategoryDto> categoryList=new ArrayList<>();
		List<BigCategory> bigCategoryList=bigcategoryRepository.findAll();
		for(BigCategory category:bigCategoryList){
			//BigCategory bigCategory=categoryRepository.findById(1L).orElseThrow(()->new IllegalArgumentException("존재하지 않는 카테고리입니다."));
			//BigCategory bigCategory=categoryRepository.findById(i).orElseThrow(()->new IllegalArgumentException("존재하지 않는 카테고리입니다."));
			int count= productRepository.countByBigCategoryAndEvent(category,event);
			if(count!=0){
				CategoryDto categoryDto=new CategoryDto();
				categoryDto.setCount(count);
				categoryDto.setName(category.getName());
				categoryDto.setBigCategoryId(category.getBigcategoryId());
				categoryList.add(categoryDto);
			}
		}
		newProductsDto.setCategoryList(categoryList);
		newProductsDto.setTotal(productDtoList.size());


		return newProductsDto;

	}

	public SearchDto getSearchList(String sword, Integer sort, Long filters){
		SearchDto searchDto = new SearchDto();
		searchDto.setSword(sword);

		ProductsListDto newProductsDto=new ProductsListDto();
		List<Product> productList= new ArrayList<>();

		//filters 선택 안한 경우
		if(filters==0L){
			if(sort==5) {//낮은가격순
				productList=productRepository.findTop12ByTitleContainingOrderByPriceAsc(sword);
			} else if (sort==4) {//높은가격순
				productList=productRepository.findTop12ByTitleContainingOrderByPriceDesc(sword);
			} else if(sort==3){//혜택순
				productList=productRepository.findTop12ByTitleContainingOrderByDiscountDesc(sword);
			} else{//나머지
				productList=productRepository.findTop12ByTitleContaining(sword);
			}
		}
		else{ //filters 선택한 경우
			BigCategory bigCategory=bigcategoryRepository.findById(filters).orElseThrow(()->new IllegalArgumentException("존재하지 않는 대카테고리입니다."));
			if(sort==5) {//낮은가격순
				productList=productRepository.findTop12ByTitleContainingAndBigCategoryOrderByPriceAsc(sword, bigCategory);
			} else if (sort==4) {//높은가격순
				productList=productRepository.findTop12ByTitleContainingAndBigCategoryOrderByPriceDesc(sword, bigCategory);
			} else if(sort==3){//혜택순
				productList=productRepository.findTop12ByTitleContainingAndBigCategoryOrderByDiscountDesc(sword, bigCategory);
			} else{//나머지
				productList=productRepository.findTop12ByTitleContainingAndBigCategory(sword, bigCategory);
			}
		}

		List<ProductDto> productDtoList=new ArrayList<>();
		for(Product product:productList){
			ProductDto productDto=new ProductDto(product);
			productDto.setReviewNum(reviewRepository.countByProduct(product));
			productDtoList.add(productDto);
		}

		newProductsDto.setProductList(productDtoList);
		List<CategoryDto> categoryList=new ArrayList<>();
		List<BigCategory> bigCategoryList=bigcategoryRepository.findAll();

		for(BigCategory category:bigCategoryList){
			int count= productRepository.countByTitleContainingAndBigCategory(sword, category);
			if(count!=0){
				CategoryDto categoryDto=new CategoryDto();
				categoryDto.setCount(count);
				categoryDto.setName(category.getName());
				categoryDto.setBigCategoryId(category.getBigcategoryId());
				categoryList.add(categoryDto);
			}
		}

		newProductsDto.setCategoryList(categoryList);
		newProductsDto.setTotal(productDtoList.size());

		//searchDto.setCount(productList.size());
		searchDto.setProductInfo(newProductsDto);

		return searchDto;

	}

	public ProductDetailDto getProductDetailDto(Product product, Long memberId){
		Member member = memberRepository.findByMemberId(memberId);
		boolean isLiked = likeRepository.existsByMemberAndProduct(member, product);
		List<String> reviewImages = findReviewImagesByProduct(product);
		List<ProductReviewDto> reviews = findReviewsByProduct(product, memberId);
		Integer reviewCount = reviews.size();
		List<ProductInquiryDto> inquiries = findInquiriesByProduct(product);
		ProductDetailDto productDetailDto = new ProductDetailDto(product, isLiked, reviewImages, reviewCount, reviews, inquiries);
		return productDetailDto;
	}

	public List<String> findReviewImagesByProduct(Product product) {
		List<Review> reviewList = reviewRepository.findAllByProduct(product);
		List<String> productReviewImageUrls = new ArrayList<>();
		reviewList.forEach(review -> {
			List<ReviewImage> reviewImageList = reviewImageRepository.findAllByReview(review);
			List<String> reviewImageUrls = reviewImageList.stream()
					.map(ReviewImage::getUrl)
					.collect(Collectors.toList());
			productReviewImageUrls.addAll(reviewImageUrls);
		});
		return productReviewImageUrls;
	}

	public List<ProductReviewDto> findReviewsByProduct(Product product, Long memberId){
		List<Review> reviewList = reviewRepository.findAllByProduct(product);
		List<ProductReviewDto> productReviewDtos = new ArrayList<>();
		reviewList.forEach(review -> {
			List<String> images = findImagesByReview(review);
			Member member = memberRepository.findByMemberId(memberId);
			if (member != null) {
				Boolean isReviewHelp = reviewHelpRepository.existsByMemberAndReview(member, review);
				ProductReviewDto productReviewDto = ProductReviewDto.from(review, images,isReviewHelp);
				productReviewDtos.add(productReviewDto);
			} else {
				ProductReviewDto productReviewDto = ProductReviewDto.from(review, images,false);
				productReviewDtos.add(productReviewDto);
			}
		});
		return productReviewDtos;
	}

	public List<String> findImagesByReview(Review review){
		List<ReviewImage> reviewImageList = reviewImageRepository.findAllByReview(review);
		return reviewImageList.stream()
				.map(ReviewImage::getUrl).collect(Collectors.toList());
	}

	public List<ProductInquiryDto> findInquiriesByProduct(Product product){
		List<Inquiry> inquiryList = inquiryRepository.findAllByProduct(product);
		List<ProductInquiryDto> productInquiryDtos = new ArrayList<>();
		inquiryList.forEach(inquiry -> {
			ProductInquiryDto productInquiryDto = ProductInquiryDto.from(inquiry);
			productInquiryDtos.add(productInquiryDto);
		});
		return productInquiryDtos;
	}
 
    // 찜하기에서 사용
    @Transactional(readOnly = true)
    public Product findProductById(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(()->new EntityNotFoundException("해당 상품이 존재하지 않습니다."));
    }
}
