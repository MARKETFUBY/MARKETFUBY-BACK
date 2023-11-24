package MARKETFUBY.BigCategory.dto;

import java.util.List;

import MARKETFUBY.BigCategory.domain.BigCategory;
import MARKETFUBY.SmallCategory.domain.SmallCategory;
import MARKETFUBY.SmallCategory.dto.SmallCategoryDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryListDto {
	private Long bigCategoryId;
	private String name;
	private List<SmallCategoryDto> children;

	@Builder
	public CategoryListDto(BigCategory bigCategory, List<SmallCategoryDto> smallCategoryDtoList){
		this.bigCategoryId= bigCategory.getBigcategoryId();
		this.name=bigCategory.getName();
		this.children=smallCategoryDtoList;
	}
}
