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

	public List<ProductDto> getProductList(String sort, Pageable pageable){
		List<Product> productList= new ArrayList<>();
		if(sort.equals("asc")){
			productList=productRepository.findAllByOrderByPriceAsc(pageable);
		} else if (sort.equals("desc")) {
			productList=productRepository.findAllByOrderByPriceDesc();
		} else if(sort.equals("dis")){
			productList=productRepository.findAllByOrderByDiscountDesc();
		} else{
			productList=productRepository.findAll();
		}
		List<ProductDto> productDtoList=new ArrayList<>();
		for(Product product:productList){
			ProductDto productDto=new ProductDto(product);
			productDtoList.add(productDto);
		}
		return productDtoList;

	}
}
