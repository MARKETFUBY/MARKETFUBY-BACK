package MARKETFUBY.Product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.dto.ProductDto;
import MARKETFUBY.Product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

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

	public List<ProductDto> getSearchList(String sword){
		List<Product> productList = productRepository.findAllByTitleContaining(sword);
		List<ProductDto> productDtoList = new ArrayList<>();

		for (Product product : productList) {
			ProductDto productDto = new ProductDto(product);
			productDtoList.add(productDto);
		}

		return productDtoList;

	}
}
