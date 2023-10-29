package MARKETFUBY.Member.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name="member")
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;
    @Column(nullable = false)
    private String fubyId;
    @Column(nullable = false)
    private String passwd;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String home;
    @Column(nullable = false)
    private Sex sex;
    @Column
    private String birthday;
    @Column
    private String level;
    @Column
    private Boolean selectAgreement;
    @Column
    private UseAgreement useAgreement;

    @Builder
    public Member(String fubyId, String passwd, String name, String email, String phone, String home, Sex sex, String birthday, String level, boolean selectAgreement, UseAgreement useAgreement){
        this.fubyId = fubyId;
        this.passwd = passwd;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.home = home;
        this.sex = sex;
        this.birthday = birthday;
        this.level = level;
        this.selectAgreement = selectAgreement;
        this. useAgreement = useAgreement;
    }
}
