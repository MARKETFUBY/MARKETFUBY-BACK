package MARKETFUBY.Cart.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class DeleteCartDto {
	private List<Long> cartProductId;
}
