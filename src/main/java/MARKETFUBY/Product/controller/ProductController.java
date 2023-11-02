package MARKETFUBY.Product.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.dto.ProductDto;
import MARKETFUBY.Product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping(value={"/market-best", "/market-best/{sort}"})
	@ResponseStatus(value= HttpStatus.OK)
	public List<ProductDto> getProductList(@PathVariable(required = false) String sort, Pageable pageable){
		if(sort==null){
			sort="null";
		}
		return productService.getProductList(sort, pageable);
	}
}
