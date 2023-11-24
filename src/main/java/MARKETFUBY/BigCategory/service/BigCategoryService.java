package MARKETFUBY.BigCategory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import MARKETFUBY.BigCategory.domain.BigCategory;
import MARKETFUBY.BigCategory.dto.CategoryListDto;
import MARKETFUBY.BigCategory.repository.BigCategoryRepository;
import MARKETFUBY.SmallCategory.dto.SmallCategoryDto;
import MARKETFUBY.SmallCategory.repository.SmallCategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BigCategoryService {
	private final BigCategoryRepository bigcategoryRepository;
	private final SmallCategoryRepository smallCategoryRepository;


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
}
