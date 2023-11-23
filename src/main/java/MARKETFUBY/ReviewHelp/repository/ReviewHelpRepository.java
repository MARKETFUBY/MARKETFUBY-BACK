package MARKETFUBY.ReviewHelp.repository;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Review.domain.Review;
import MARKETFUBY.ReviewHelp.domain.ReviewHelp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewHelpRepository extends JpaRepository<ReviewHelp, Long> {
    boolean existsByMemberAndReview(Member member, Review review);
}
