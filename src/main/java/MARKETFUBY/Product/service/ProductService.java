package MARKETFUBY.Product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import MARKETFUBY.BigCategory.domain.BigCategory;
import MARKETFUBY.BigCategory.dto.CategoryDto;
import MARKETFUBY.BigCategory.repository.CategoryRepository;
import MARKETFUBY.Event.domain.Event;
import MARKETFUBY.Event.repository.EventRepository;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.dto.NewProductsDto;
import MARKETFUBY.Product.dto.ProductDto;
import MARKETFUBY.Product.dto.SearchDto;
import MARKETFUBY.Product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final EventRepository eventRepository;
	private final CategoryRepository categoryRepository;

	public List<ProductDto> getProductList(Integer sort, Pageable pageable){
		List<Product> productList= new ArrayList<>();
		if(sort==5) {//낮은가격순
			productList=productRepository.findAllByOrderByPriceAsc();
		} else if (sort==4) {//놓은가격순
			productList=productRepository.findAllByOrderByPriceDesc();
		} else if(sort==3){//혜택순
			productList=productRepository.findAllByOrderByDiscountDesc();
		} else{//추천순
			productList=productRepository.findAll();
		}
		List<ProductDto> productDtoList=new ArrayList<>();
		for(Product product:productList){
			ProductDto productDto=new ProductDto(product);
			productDtoList.add(productDto);
		}
		return productDtoList;

	}

	public NewProductsDto getNewProductList(Integer sort, Pageable pageable){
		NewProductsDto newProductsDto=new NewProductsDto();
		List<Product> productList= new ArrayList<>();
		Event event=eventRepository.findById(1L).orElseThrow(()->new IllegalArgumentException("존재하지 않는 이벤트입니다."));
		if(sort==5) {//낮은가격순
			productList=productRepository.findByEventOrderByPriceAsc(event);
		} else if (sort==4) {//놓은가격순
			productList=productRepository.findByEventOrderByPriceDesc(event);
		} else if(sort==3){//혜택순
			productList=productRepository.findByEventOrderByDiscountDesc(event);
		} else{//추천순
			productList=productRepository.findAllByEvent(event);
		}
		List<ProductDto> productDtoList=new ArrayList<>();
		for(Product product:productList){
			ProductDto productDto=new ProductDto(product);
			productDtoList.add(productDto);
		}
		newProductsDto.setEventUrl(event.getImage());
		newProductsDto.setProductList(productDtoList);
		List<CategoryDto> categoryList=new ArrayList<>();
		List<BigCategory> bigCategoryList=categoryRepository.findAll();
		for(BigCategory bigCategory:bigCategoryList){
			//BigCategory bigCategory=categoryRepository.findById(1L).orElseThrow(()->new IllegalArgumentException("존재하지 않는 카테고리입니다."));
			//BigCategory bigCategory=categoryRepository.findById(i).orElseThrow(()->new IllegalArgumentException("존재하지 않는 카테고리입니다."));
			if(productRepository.existsByBigCategory(bigCategory)){
				int count= productRepository.countByBigCategoryAndEvent(bigCategory,event);
				CategoryDto categoryDto=new CategoryDto();
				categoryDto.setCount(count);
				categoryDto.setName(bigCategory.getName());
				categoryList.add(categoryDto);
			}
		}
		newProductsDto.setCategoryList(categoryList);
		newProductsDto.setTotal(productDtoList.size());

		return newProductsDto;

	}

	public SearchDto getSearchList(String sword){
		SearchDto searchDto = new SearchDto();
		searchDto.setSword(sword);
		List<Product> productList = productRepository.findAllByTitleContaining(sword);
		List<ProductDto> productDtoList = new ArrayList<>();

		for (Product product : productList) {
			ProductDto productDto = new ProductDto(product);
			productDtoList.add(productDto);
		}
		searchDto.setCount(productList.size());
		searchDto.setProductList(productDtoList);

		return searchDto;

	}
}
