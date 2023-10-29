package MARKETFUBY.Inquiry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.Inquiry.domain.Inquiry;
import MARKETFUBY.Member.domain.Member;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
	List<Inquiry> findAllByWriter(Member member);
}
