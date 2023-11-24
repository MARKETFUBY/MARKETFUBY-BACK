package MARKETFUBY.BigCategory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MARKETFUBY.BigCategory.dto.CategoryListDto;
import MARKETFUBY.BigCategory.service.BigCategoryService;
import MARKETFUBY.Cart.dto.CartDto;
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
}
