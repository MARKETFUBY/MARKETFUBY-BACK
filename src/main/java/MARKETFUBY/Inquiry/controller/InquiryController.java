package MARKETFUBY.Inquiry.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MARKETFUBY.Inquiry.dto.InquiryDto;
import MARKETFUBY.Inquiry.dto.InquiryRequestDto;
import MARKETFUBY.Inquiry.dto.InquiryResponseDto;
import MARKETFUBY.Inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage/inquiry")
@RequiredArgsConstructor
public class InquiryController {

	private final InquiryService inquiryService;

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public InquiryResponseDto getInquiryInfo(){
		return inquiryService.getInquiryInfo();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public String postInquiry(@RequestBody InquiryRequestDto inquiryRequestDto){
		inquiryService.create(inquiryRequestDto);
		return "문의가 정상적으로 등록되었습니다.";
	}

	@PutMapping("/{inquiryId}")
	@ResponseStatus(value = HttpStatus.OK)
	public String updateInquiry(@PathVariable Long inquiryId, @RequestBody InquiryRequestDto inquiryRequestDto){
		inquiryService.update(inquiryId, inquiryRequestDto);
		return "문의가 정상적으로 수정되었습니다.";
	}
}
