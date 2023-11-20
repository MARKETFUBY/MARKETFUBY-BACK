package MARKETFUBY.Order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MARKETFUBY.Order.dto.OrderResponseDto;
import MARKETFUBY.Order.repository.OrderRepository;
import MARKETFUBY.Order.service.OrderService;
import MARKETFUBY.Product.dto.MainDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value="mypage/order")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public OrderResponseDto getOrderList() {
		return orderService.getOrderList();
	}
}
