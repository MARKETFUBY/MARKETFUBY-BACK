package MARKETFUBY.ReviewImage.repository;

import MARKETFUBY.Inquiry.domain.Inquiry;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.ReviewImage.domain.ReviewImage;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    List<ReviewImage> findAllByProduct(Product product);
    List<ReviewImage> findAllByReview(Review review);
}
