package MARKETFUBY.Member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name="member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Member(String fubyId, String passwd, String name, String email, String phone, String home, Sex sex, String birthday, String level) {
        this.fubyId = fubyId;
        this.passwd = passwd;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.home = home;
        this.sex = sex;
        this.birthday = birthday;
        this.level = level;
    }
}