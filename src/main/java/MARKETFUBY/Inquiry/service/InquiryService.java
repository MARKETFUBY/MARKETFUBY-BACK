package MARKETFUBY.Inquiry.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import MARKETFUBY.Inquiry.domain.Inquiry;
import MARKETFUBY.Inquiry.dto.InquiryDto;
import MARKETFUBY.Inquiry.dto.InquiryRequestDto;
import MARKETFUBY.Inquiry.dto.InquiryResponseDto;
import MARKETFUBY.Inquiry.repository.InquiryRepository;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Member.service.MemberService;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InquiryService {
	private final InquiryRepository inquiryRepository;
	private final ProductRepository productRepository;
	private final MemberService memberService;

	@Transactional(readOnly = true)
	public InquiryResponseDto getInquiryInfo(){
		InquiryResponseDto inquiryResponseDto= new InquiryResponseDto();

		//현재 로그인 중인 사용자 불러오기
		Member member= memberService.getCurrentMember();

		inquiryResponseDto.setName(member.getName());
		inquiryResponseDto.setLevel(member.getLevel());

		List<Inquiry> inquiryList=inquiryRepository.findAllByWriter(member);
		List<InquiryDto> inquiryDtoList=new ArrayList<>();
		for(Inquiry inquiry:inquiryList){
			inquiryDtoList.add(new InquiryDto(inquiry));
		}

		inquiryResponseDto.setInquiryList(inquiryDtoList);
		return inquiryResponseDto;

	}

	public void create(InquiryRequestDto inquiryRequestDto){
		//현재 로그인 중인 사용자 불러오기
		Member member= memberService.getCurrentMember();

		Long productId= inquiryRequestDto.getProductId();

		Product product=productRepository.findById(productId)
			.orElseThrow(()->new IllegalArgumentException("존재하지 않는 제품입니다."));
		inquiryRepository.save(inquiryRequestDto.toEntity(member, product));
	}

	public void update(Long inquiryId, InquiryRequestDto inquiryRequestDto){
		Inquiry inquiry=inquiryRepository.findById(inquiryId)
			.orElseThrow(()->new IllegalArgumentException("존재하지 않는 inquiryId입니다."));
		inquiry.updateInquiry(inquiryRequestDto.getTitle(), inquiryRequestDto.getContent(), inquiryRequestDto.getIsSecret());
		inquiryRepository.save(inquiry);
	}
}
