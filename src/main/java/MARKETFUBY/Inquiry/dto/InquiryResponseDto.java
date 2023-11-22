package MARKETFUBY.Inquiry.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InquiryResponseDto {
	private String name;
	private String level;
	private List<InquiryDto> inquiryList;
}
