package MARKETFUBY.Review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {
	List<Review> findAllByWriter(Member writer);
	Integer countByProduct(Product product);
	List<Review> findAllByProduct(Product product);
}
