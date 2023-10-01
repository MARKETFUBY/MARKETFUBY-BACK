package MARKETFUBY.Like.domain;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "like")
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public Like(Member member, Product product){
        this.member = member;
        this.product = product;
    }
}
