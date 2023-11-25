package MARKETFUBY.Product.controller;

import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.dto.ProductDetailDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import MARKETFUBY.Product.dto.MainDto;
import MARKETFUBY.Product.dto.ProductsListDto;
import MARKETFUBY.Product.dto.SearchDto;
import MARKETFUBY.Product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping(value = "/main")
	@ResponseStatus(value = HttpStatus.OK)
	public MainDto getMainList() {
		return productService.getMainList();
	}

	@GetMapping(value = "/collections/market-newproduct")
	@ResponseStatus(value = HttpStatus.OK)
	public ProductsListDto getNewProductList(@RequestParam(required = false) Integer sort, @RequestParam(required = false) Long filters) {
		if (sort == null) {
			sort = 0;
		}
		if (filters == null) {
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
		if (filters == null) {
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
		if (filters == null) {
			filters = 0L;
		}
		return productService.getProductList(sort, filters, 3l);
	}

	@GetMapping(value = "/search")
	@ResponseStatus(value = HttpStatus.OK)
	public SearchDto getSearchList(@RequestParam String sword, @RequestParam(required = false) Integer sort, @RequestParam(required = false) Long filters) {
		if (sort == null) {
			sort = 0;
		}
		if (filters == null) {
			filters = 0L;
		}
		return productService.getSearchList(sword, sort, filters);
	}

	@GetMapping("/products/{productId}")
	@ResponseStatus(value = HttpStatus.OK)
	public ProductDetailDto getProductDetail(@PathVariable Long productId, @RequestParam Long memberId){
	    Product product = productService.findProductById(productId);
	    return productService.getProductDetailDto(product, memberId);
	}
}
