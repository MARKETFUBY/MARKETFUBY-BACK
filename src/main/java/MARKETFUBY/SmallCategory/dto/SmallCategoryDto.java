package MARKETFUBY.SmallCategory.dto;

import MARKETFUBY.SmallCategory.domain.SmallCategory;
import lombok.Getter;

@Getter
public class SmallCategoryDto {
	private Long smallCategoryId;
	private String name;

	public SmallCategoryDto(SmallCategory smallCategory){
		this.smallCategoryId=smallCategory.getSmallcategoryId();
		this.name=smallCategory.getName();
	}
}
