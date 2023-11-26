package MARKETFUBY.ReviewImage.repository;

import MARKETFUBY.Review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.ReviewImage.domain.ReviewImage;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    List<ReviewImage> findAllByReview(Review review);
}
