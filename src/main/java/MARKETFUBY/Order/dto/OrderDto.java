package MARKETFUBY.Order.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import MARKETFUBY.OrderProduct.domain.OrderProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
*"image":"image3.jpg",
        "datetime":"2023.09.14(15시18분)",
        "title":"이유식 재료 친환경 양배추 1/4통 외 7건",
        "orderId":32351,
        "paymentType":"네이버페이",
        "price":40272,
        "deliveryStatus":"배송완료"
*  */
@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
	private String imageUrl;
	private LocalDate date;
	private String title;
	private Long orderId;
	private String paymentType;
	private Long price;
	private String deliveryStatus;

	@Builder
	public OrderDto(String imageUrl, LocalDate date, String title, Long orderId, String paymentType, Long price, String deliveryStatus){
		this.imageUrl=imageUrl;
		this.date=date;
		this.title=title;
		this.orderId=orderId;
		this.paymentType=paymentType;
		this.price=price;
		this.deliveryStatus=deliveryStatus;
	}
}
