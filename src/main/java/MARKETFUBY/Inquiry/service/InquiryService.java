package MARKETFUBY.Inquiry.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import MARKETFUBY.Inquiry.domain.Inquiry;
import MARKETFUBY.Inquiry.dto.InquiryDto;
import MARKETFUBY.Inquiry.repository.InquiryRepository;
import MARKETFUBY.Member.domain.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InquiryService {
	private final InquiryRepository inquiryRepository;

	@Transactional(readOnly = true)
	public List<InquiryDto> getInquiryInfo(){
		//현재 로그인 중인 사용자 불러오는 단계 필요
		Long memberId=1L;
		Member member=new Member();
		member.setMemberId(1L);
		List<Inquiry> inquiryList=inquiryRepository.findAllByWriter(member);
		List<InquiryDto> inquiryDtoList=new ArrayList<>();
		for(Inquiry inquiry:inquiryList){
			inquiryDtoList.add(new InquiryDto(inquiry));
		}
		return inquiryDtoList;

	}
}
