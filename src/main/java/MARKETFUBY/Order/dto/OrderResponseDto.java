package MARKETFUBY.Order.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
* {
    "name":"최윤지",
    "level":"화이트",
    "orderList":[
      {
        "image":"image1.jpg",
        "datetime":"2023.09.14(15시18분)",
        "title":"이유식 재료 친환경 양배추 1/4통 외 7건",
        "orderId":32351,
        "paymentType":"네이버페이",
        "price":40272,
        "deliveryStatus":"배송완료"
      },
			{
        "image":"image2.jpg",
        "datetime":"2023.09.14(15시18분)",
        "title":"이유식 재료 친환경 양배추 1/4통 외 7건",
        "orderId":32351,
        "paymentType":"네이버페이",
        "price":40272,
        "deliveryStatus":"배송완료"
      },
			{
        "image":"image3.jpg",
        "datetime":"2023.09.14(15시18분)",
        "title":"이유식 재료 친환경 양배추 1/4통 외 7건",
        "orderId":32351,
        "paymentType":"네이버페이",
        "price":40272,
        "deliveryStatus":"배송완료"
      }
      ]
}
* */
@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDto {
	private String name;
	private String level;
	private List<OrderDto> orderList;
}
