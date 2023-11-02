package MARKETFUBY.Cart.dto;

import MARKETFUBY.Cart.domain.Cart;
import MARKETFUBY.CartProduct.domain.CartProduct;
import MARKETFUBY.CartProduct.dto.PostCartProductDto;
import MARKETFUBY.Product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCartDto {
	//productId, count
	//private Long cartId;
	private Long productId;
	private Long count;

	public CartProduct toEntity(Cart cart, Product product, PostCartDto postCartDto){
		return CartProduct.builder()
			.cart(cart)
			.product(product)
			.count(postCartDto.getCount())
			.build();
	}
}
