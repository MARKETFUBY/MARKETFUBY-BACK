package MARKETFUBY.Product.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
	private String sword;
	private Integer count;
	private List<ProductDto> productList;
}
