package MARKETFUBY.Product.dto;

import MARKETFUBY.Product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

//이미지, 배송타입, 브랜드, 제목, 부가제목, 가격, 할인, 후기 수
@Getter
@NoArgsConstructor
public class ProductDto {
	private Long productId;
	private String image;
	private String delivery;
	private Long bigcategoryId;
	private String brand;
	private String title;
	private String subtitle;
	private Long price;
	private Long discount;

	public ProductDto(Product product){
		this.productId=product.getProductId();
		this.image=product.getImage();
		this.delivery=product.getDelivery();
		this.bigcategoryId=product.getBigCategory().getBigcategoryId();
		this.brand=product.getBrand();
		this.title=product.getTitle();
		this.subtitle=product.getSubtitle();
		this.price= product.getPrice();
		this.discount=product.getDiscount();
	}

}
