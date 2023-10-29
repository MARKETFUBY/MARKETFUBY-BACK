package MARKETFUBY.Cart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MARKETFUBY.Cart.dto.CartDto;
import MARKETFUBY.Cart.service.CartService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;

	//cart랑 cartproduct 합쳐도 될 것 같다..?memberId 필드만 추가해서
	//장바구니 목록들도 들어가서 따로 필요하네...흠
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public CartDto getCartInfo(){
		//System.out.println("hi");
		return cartService.getCartInfo();
		//return "HEllo";
	}
}
