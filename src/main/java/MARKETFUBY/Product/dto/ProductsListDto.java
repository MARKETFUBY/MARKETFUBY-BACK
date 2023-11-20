package MARKETFUBY.Product.dto;

import java.util.List;

import MARKETFUBY.BigCategory.dto.CategoryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductsListDto {
	private List<ProductDto> productList;
	private List<CategoryDto> categoryList;
	private Integer total;
}
