package MARKETFUBY.BigCategory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MARKETFUBY.BigCategory.dto.CategoryListDto;
import MARKETFUBY.BigCategory.service.BigCategoryService;
import MARKETFUBY.Cart.dto.CartDto;
import MARKETFUBY.Product.dto.ProductDto;
import MARKETFUBY.Product.dto.ProductsListDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class BigCategoryController {
	private final BigCategoryService bigCategoryService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<CategoryListDto> getCategoryList(){
		return bigCategoryService.getCategoryList();
	}

	@GetMapping
	@RequestMapping("/{categoryId}")
	public ProductsListDto getCategoryProductList(@PathVariable Long categoryId, @RequestParam(required = false) Integer sort){
		if (sort == null) {
			sort = 0;
		}
		return bigCategoryService.getCategoryProductList(categoryId, sort);
	}
}
