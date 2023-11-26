package MARKETFUBY.ReviewHelp.repository;

import MARKETFUBY.Like.domain.Like;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Review.domain.Review;
import MARKETFUBY.ReviewHelp.domain.ReviewHelp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewHelpRepository extends JpaRepository<ReviewHelp, Long> {
    boolean existsByMemberAndReview(Member member, Review review);
    Optional<ReviewHelp> findByMemberAndReview(Member member, Review review);
    List<ReviewHelp> findAllByMember(Member member);
}
