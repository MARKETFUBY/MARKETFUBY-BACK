package MARKETFUBY.BigCategory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	private Long bigCategoryId;
	private String name;
	private Integer count;
}
