package MARKETFUBY.Inquiry.domain;

import MARKETFUBY.Global.entity.BaseTimeEntity;
import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Product.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "inquiry")
@NoArgsConstructor
public class Inquiry extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id", updatable = false)
    private Long inquiryId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member writer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Status status;

    @Column
    private Boolean isSecret;

    @Builder
    public Inquiry(Member writer, Product product, String title, String content, Status status, boolean isSecret){
        this.writer = writer;
        this.product = product;
        this.title = title;
        this.content = content;
        this.status = status;
        this.isSecret = isSecret;
    }
    public void updateInquiry(String title, String content, Boolean isSecret){
        this.title = title;
        this.content = content;
        this.isSecret = isSecret;
    }
}
