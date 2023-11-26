package MARKETFUBY.ReviewImage.domain;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Product.domain.Product;
import MARKETFUBY.Review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reviewimage")
@NoArgsConstructor
public class ReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", updatable = false)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Getter
    @Column
    private String url;

    @Builder
    public ReviewImage(Review review, String url){
        this.review = review;
        this.url = url;
    }
}
