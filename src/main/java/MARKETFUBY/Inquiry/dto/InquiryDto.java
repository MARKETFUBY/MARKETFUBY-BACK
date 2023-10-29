package MARKETFUBY.Inquiry.dto;

import java.time.LocalDate;

import MARKETFUBY.Inquiry.domain.Inquiry;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InquiryDto {
	//제목,작성일,답변상태
	private String title;
	private LocalDate date;
	private String status;

	public InquiryDto(Inquiry inquiry){
		this.title= inquiry.getTitle();
		this.date=inquiry.getCreatedAt().toLocalDate();
		this.status=inquiry.getStatus().getContent();
	}
}
