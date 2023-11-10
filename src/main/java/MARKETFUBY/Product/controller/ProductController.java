package MARKETFUBY.Product.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.dto.NewProductsDto;
import MARKETFUBY.Product.dto.ProductDto;
import MARKETFUBY.Product.dto.SearchDto;
import MARKETFUBY.Product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping(value = "/collections/market-best")
	@ResponseStatus(value = HttpStatus.OK)
	public List<ProductDto> getProductList(@RequestParam(required = false) Integer sort, Pageable pageable) {
		if (sort == null) {
			sort = 0;
		}
		return productService.getProductList(sort, pageable);
	}

	@GetMapping(value = "/collections/market-newproduct")
	@ResponseStatus(value = HttpStatus.OK)
	public NewProductsDto getNewProductList(@RequestParam(required = false) Integer sort, Pageable pageable) {
		if (sort == null) {
			sort = 0;
		}
		return productService.getNewProductList(sort, pageable);
	}

	@GetMapping(value = "/search")
	@ResponseStatus(value=HttpStatus.OK)
	public SearchDto getSearchList(@RequestParam(required = false) String sword){
		return productService.getSearchList(sword);

	}
}
