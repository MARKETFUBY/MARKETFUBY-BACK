package MARKETFUBY.BigCategory.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bigcategory")
@NoArgsConstructor
public class BigCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bigcategory_id", updatable = false)
    private Long bigcategoryId;
    @Column(nullable = false)
    private String name;

    @Builder
    public BigCategory(Long bigcategoryId, String name){
        this.bigcategoryId = bigcategoryId;
        this.name = name;
    }
}
