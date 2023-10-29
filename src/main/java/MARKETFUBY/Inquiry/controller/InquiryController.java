package MARKETFUBY.Inquiry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MARKETFUBY.Inquiry.dto.InquiryDto;
import MARKETFUBY.Inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage/inquiry/products")
@RequiredArgsConstructor
public class InquiryController {

	private final InquiryService inquiryService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<InquiryDto> getInquiryInfo(){
		return inquiryService.getInquiryInfo();
	}
}
