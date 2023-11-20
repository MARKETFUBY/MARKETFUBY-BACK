package MARKETFUBY.Cart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MARKETFUBY.Cart.dto.CartDto;
import MARKETFUBY.Cart.dto.DeleteCartDto;
import MARKETFUBY.Cart.dto.PostCartDto;
import MARKETFUBY.Cart.service.CartService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public CartDto getCartInfo(){
		return cartService.getCartInfo();
	}

	@PostMapping
	@ResponseStatus(value=HttpStatus.CREATED)
	public String postCart(@RequestBody PostCartDto postCartDto){
		cartService.postCart(postCartDto);
		return "장바구니에 추가되었습니다.";
	}

	@PutMapping
	@ResponseStatus(value=HttpStatus.OK)
	public String updateCart(@RequestBody PostCartDto postCartDto){
		cartService.updateCart(postCartDto);
		return "장바구니가 수정되었습니다.";
	}

	@DeleteMapping
	@ResponseStatus(value=HttpStatus.OK)
	public String deleteCart(@RequestBody DeleteCartDto deleteCartDto){
		cartService.deleteCart(deleteCartDto);
		return "장바구니에서 제품이 삭제되었습니다.";
	}
}
