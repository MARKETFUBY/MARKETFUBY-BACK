package MARKETFUBY.ReviewHelp.service;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Member.service.MemberService;
import MARKETFUBY.Review.domain.Review;
import MARKETFUBY.Review.service.ReviewService;
import MARKETFUBY.ReviewHelp.domain.ReviewHelp;
import MARKETFUBY.ReviewHelp.repository.ReviewHelpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewHelpService {
    private final MemberService memberService;
    private final ReviewHelpRepository reviewHelpRepository;

    @Lazy
    @Autowired
    private ReviewService reviewService;

    public void createReviewHelp(Long reviewId, Long memberId){
        Review review = reviewService.findReviewById(reviewId);
        Member member = memberService.findMemberById(memberId);

        if(isExistsByMemberAndReview(member, review)){
            throw new RuntimeException("이미 도움돼요가 반영된 리뷰입니다.");
        }

        ReviewHelp reviewHelp = ReviewHelp.builder()
                .review(review)
                .member(member)
                .build();

        reviewHelpRepository.save(reviewHelp);
    }

    @Transactional(readOnly = true)
    public boolean isExistsByMemberAndReview(Member member, Review review){
        return reviewHelpRepository.existsByMemberAndReview(member, review);
    }
}
