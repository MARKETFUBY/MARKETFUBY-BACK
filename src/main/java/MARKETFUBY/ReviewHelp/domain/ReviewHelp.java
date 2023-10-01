package MARKETFUBY.ReviewHelp.domain;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "reviewhelp")
@NoArgsConstructor
public class ReviewHelp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long helpId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @Builder
    public ReviewHelp(Member member, Review review){
        this.member = member;
        this.review = review;
    }
}
