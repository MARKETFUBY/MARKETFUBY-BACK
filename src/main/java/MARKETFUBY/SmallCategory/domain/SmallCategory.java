package MARKETFUBY.SmallCategory.domain;

import MARKETFUBY.BigCategory.domain.BigCategory;
import MARKETFUBY.Member.domain.Member;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "smallcategory")
@NoArgsConstructor
public class SmallCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "smallcategory_id", updatable = false)
    private Long smallcategoryId;
    @ManyToOne
    @JoinColumn(name = "bigcategory_id", nullable = false)
    private BigCategory bigCategory;
    @Column(nullable = false)
    private String name;

    @Builder
    public SmallCategory(Long smallcategoryId, BigCategory bigCategory, String name){
        this.smallcategoryId = smallcategoryId;
        this.bigCategory = bigCategory;
        this.name = name;
    }
}