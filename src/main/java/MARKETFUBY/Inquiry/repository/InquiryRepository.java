package MARKETFUBY.Inquiry.repository;

import java.util.List;
import java.util.Optional;

import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.Inquiry.domain.Inquiry;
import MARKETFUBY.Member.domain.Member;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
	List<Inquiry> findAllByWriter(Member member);
	Optional<Inquiry> findById(Long inquiryId);
	List<Inquiry> findAllByProduct(Product product);
}
