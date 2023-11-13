package MARKETFUBY.Product.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MARKETFUBY.Product.dto.ProductsListDto;
import MARKETFUBY.Product.dto.ProductDto;
import MARKETFUBY.Product.dto.SearchDto;
import MARKETFUBY.Product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping(value = "/collections")
	@ResponseStatus(value = HttpStatus.OK)
	public List<ProductDto> getProductList(@RequestParam(required = false) Integer sort, Pageable pageable) {
		if (sort == null) {
			sort = 0;
		}
		return productService.getProductList(sort, pageable);
	}

	@GetMapping(value = "/collections/market-newproduct")
	@ResponseStatus(value = HttpStatus.OK)
	public ProductsListDto getNewProductList(@RequestParam(required = false) Integer sort, @RequestParam(required = false) Long filters) {
		if (sort == null) {
			sort = 0;
		}
		if (filters == null){
			filters = 0L;
		}
		return productService.getProductList(sort, filters, 1l);
	}

	@GetMapping(value = "/collections/market-best")
	@ResponseStatus(value = HttpStatus.OK)
	public ProductsListDto getBestProductList(@RequestParam(required = false) Integer sort, @RequestParam(required = false) Long filters) {
		if (sort == null) {
			sort = 0;
		}
		if (filters == null){
			filters = 0L;
		}
		return productService.getProductList(sort, filters, 2l);
	}

	@GetMapping(value = "/collections/market-time-sales")
	@ResponseStatus(value = HttpStatus.OK)
	public ProductsListDto getTimeProductList(@RequestParam(required = false) Integer sort, @RequestParam(required = false) Long filters) {
		if (sort == null) {
			sort = 0;
		}
		if (filters == null){
			filters = 0L;
		}
		return productService.getProductList(sort, filters, 3l);
	}

	@GetMapping(value = "/search")
	@ResponseStatus(value=HttpStatus.OK)
	public SearchDto getSearchList(@RequestParam String sword,@RequestParam(required = false) Integer sort, @RequestParam(required = false) Long filters){
		if (sort == null) {
			sort = 0;
		}
		if (filters == null){
			filters = 0L;
		}
		return productService.getSearchList(sword, sort, filters);
	}
}
