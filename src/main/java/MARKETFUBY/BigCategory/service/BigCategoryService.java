package MARKETFUBY.BigCategory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import MARKETFUBY.BigCategory.domain.BigCategory;
import MARKETFUBY.BigCategory.dto.CategoryDto;
import MARKETFUBY.BigCategory.dto.CategoryListDto;
import MARKETFUBY.BigCategory.repository.BigCategoryRepository;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.dto.ProductDto;
import MARKETFUBY.Product.dto.ProductsListDto;
import MARKETFUBY.Product.repository.ProductRepository;
import MARKETFUBY.Review.repository.ReviewRepository;
import MARKETFUBY.SmallCategory.domain.SmallCategory;
import MARKETFUBY.SmallCategory.dto.SmallCategoryDto;
import MARKETFUBY.SmallCategory.repository.SmallCategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BigCategoryService {
	private final BigCategoryRepository bigcategoryRepository;
	private final SmallCategoryRepository smallCategoryRepository;
	private final ProductRepository productRepository;
	private final ReviewRepository reviewRepository;


	public List<CategoryListDto> getCategoryList() {
		List<BigCategory> bigCategoryList = bigcategoryRepository.findAll();
		List<CategoryListDto> categoryList = new ArrayList<>();
		for (BigCategory bigCategory : bigCategoryList) {
			List<SmallCategoryDto> smallCategoryDtoList = smallCategoryRepository.findAllByBigCategory(bigCategory)
				.stream()
				.map(SmallCategoryDto::new)
				.collect(Collectors.toList());
			categoryList.add(new CategoryListDto(bigCategory, smallCategoryDtoList));
		}

		return categoryList;
	}

	public ProductsListDto getCategoryProductList(Long categoryId, Integer sort){
		SmallCategory smallCategory=smallCategoryRepository.findById(categoryId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 소카테고리입니다."));
		ProductsListDto newProductsDto=new ProductsListDto();
		List<Product> productList= new ArrayList<>();

		if(sort==5) {//낮은가격순
			productList=productRepository.findTop12BySmallCategoryOrderByPriceAsc(smallCategory);
		} else if (sort==4) {//높은가격순
			productList=productRepository.findAllByOrderByPriceDesc();
		} else if(sort==3){//혜택순
			productList=productRepository.findAllByOrderByDiscountDesc();
		} else{//추천순
			productList=productRepository.findTop12BySmallCategory(smallCategory);
		}
		List<ProductDto> productDtoList=new ArrayList<>();
		for(Product product:productList){
			ProductDto productDto=new ProductDto(product);
			productDto.setReviewNum(reviewRepository.countByProduct(product));
			productDtoList.add(productDto);
		}

		newProductsDto.setProductList(productDtoList);

		newProductsDto.setTotal(productDtoList.size());
		return newProductsDto;
	}


}
