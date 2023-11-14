package MARKETFUBY.Cart.dto;

/*
{
//상온 상품
  "roomTempList":[
    {
      "image":"image1.jpg",
      "name":"[서울우유] 나 100% 저지방 우유 1.8L",
      "count":1,
      "price":5490,
      "discount":0
    },
    {
      "image":"image1.jpg",
      "name":"[서울우유] 나 100% 저지방 우유 1.8L",
      "count":1,
      "price":5490,
      "discount":0
    }
    ]
//냉장 상품
  "refrigeList":[
    {
      "image":"image1.jpg",
      "name":"[서울우유] 나 100% 저지방 우유 1.8L",
      "count":1,
      "price":5490,
      "discount":0
    },
    {
      "image":"image1.jpg",
      "name":"[서울우유] 나 100% 저지방 우유 1.8L",
      "count":1,
      "price":5490,
      "discount":0
    }
    ]
//냉동 상품
  "frozenList":[
    {
      "image":"image1.jpg",
      "name":"[서울우유] 나 100% 저지방 우유 1.8L",
      "count":1,
      "price":5490,
      "discount":0
    },
    {
      "image":"image1.jpg",
      "name":"[서울우유] 나 100% 저지방 우유 1.8L",
      "count":1,
      "price":5490,
      "discount":0
    }
    ]
}
//배송지
 */

import java.util.List;

import MARKETFUBY.CartProduct.domain.CartProduct;
import MARKETFUBY.Product.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDto {

	private List<SingleProduct> roomTempList;
	private List<SingleProduct> refrigeList;
	private List<SingleProduct> frozenList;
	private Integer totalAmount;
	private Integer discountAmount;
	private Integer paymentAmount;

	@Getter
	public static class SingleProduct{
		private String imageUrl;
		private String productName;
		private Long count;
		private Long price;
		private Long discount;

		public SingleProduct(CartProduct cartProduct){
			this.imageUrl=cartProduct.getProduct().getImage();
			this.productName=cartProduct.getProduct().getTitle();
			this.count=cartProduct.getCount();
			this.price=cartProduct.getProduct().getPrice();
			this.discount=cartProduct.getProduct().getDiscount();
		}

		public static SingleProduct of(CartProduct cartProduct){
			return new SingleProduct(cartProduct);
		}

	}
}
