package MARKETFUBY.Inquiry.dto;

import MARKETFUBY.Inquiry.domain.Inquiry;
import MARKETFUBY.Inquiry.domain.Status;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class InquiryRequestDto {
	private Long productId;
	private String title;
	private String content;
	private Boolean isSecret;

	public InquiryRequestDto(Long productId, String title, String content, Boolean isSecret){
		this.productId=productId;
		this.title=title;
		this.content=content;
		this.isSecret=isSecret;
	}
	public Inquiry toEntity(Member member, Product product){
		return Inquiry.builder()
			.product(product)
			.title(title)
			.content(content)
			.isSecret(isSecret)
			.writer(member)
			.status(Status.WAIT)
			.build();
	}
}
