package MARKETFUBY.Review.domain;

import java.util.List;

import MARKETFUBY.Global.entity.BaseTimeEntity;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @Column
    private String content;

    @Builder
    public Review(Member writer, Product product, String content){
        this.writer = writer;
        this.product = product;
        this.content = content;
    }
    public void updateReview(String content){
        this.content = content;
    }
}
