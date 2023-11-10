package MARKETFUBY.Review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MARKETFUBY.Review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
