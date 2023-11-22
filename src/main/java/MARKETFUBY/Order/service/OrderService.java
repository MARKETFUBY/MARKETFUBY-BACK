package MARKETFUBY.Order.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Member.repository.MemberRepository;
import MARKETFUBY.Member.service.MemberService;
import MARKETFUBY.Order.domain.Order;
import MARKETFUBY.Order.dto.OrderDto;
import MARKETFUBY.Order.dto.OrderResponseDto;
import MARKETFUBY.Order.repository.OrderRepository;
import MARKETFUBY.OrderProduct.domain.OrderProduct;
import MARKETFUBY.OrderProduct.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final OrderProductRepository orderProductRepository;
	private final MemberService memberService;

	public OrderResponseDto getOrderList(){

		OrderResponseDto orderResponseDto =new OrderResponseDto();
		List<OrderDto> orderDtoList=new ArrayList<>();

		//현재 로그인 중인 사용자 불러오기
		Member member= memberService.getCurrentMember();

		orderResponseDto.setName(member.getName());
		orderResponseDto.setLevel(member.getLevel());

		//orderId로 orderproduct하나 찾고, orderproduct 개수 찾고
		List<Order> orderList=orderRepository.findAllByOrderer(member);
		for(Order order:orderList){
			List<OrderProduct> orderProduct=orderProductRepository.findAllByOrder(order);
			int count = orderProduct.size();
			OrderProduct TopOrderProduct=orderProductRepository.findTop1ByOrder(order).orElseThrow(()->new IllegalArgumentException("존재하지 않는 주문내역제품입니다."));
			String title="";
			if(count!=1){
				title=TopOrderProduct.getProduct().getTitle()+"외 "+(count-1)+"종";
			}
			else{
				title=TopOrderProduct.getProduct().getTitle();
			}
			OrderDto orderDto=OrderDto.builder()
				.imageUrl(TopOrderProduct.getProduct().getImage())
				.date(order.getDate())
				.title(title)
				.orderId(order.getOrderId())
				.paymentType(order.getPaymentType())
				.price(order.getTotalPrice())
				.deliveryStatus(order.getStatus())
				.build();
			orderDtoList.add(orderDto);
		}
		orderResponseDto.setOrderList(orderDtoList);

		return orderResponseDto;
	}
}
