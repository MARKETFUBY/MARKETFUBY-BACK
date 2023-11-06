package MARKETFUBY.Member.service;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Member.domain.Sex;
import MARKETFUBY.Member.domain.UseAgreement;
import MARKETFUBY.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    public String join(String fubyId, String passwd, String name, String email, String phone, String home, String sex, String birthday, String level, boolean selectAgreement, String useAgreement) {
        // fubyId 중복 체크
        if(existsByFubyId(fubyId)) throw new RuntimeException(fubyId + "은 이미 존재하는 아이디입니다!");

        // 중복되지 않는다면 저장
        memberRepository.save(
                Member.builder()
                        .fubyId(fubyId)
                        .passwd(encoder.encode(passwd))
                        .name(name)
                        .email(email)
                        .phone(phone)
                        .home(home)
                        .sex(Sex.valueOf(sex))
                        .birthday(birthday)
                        .level(level)
                        .selectAgreement(selectAgreement)
                        .useAgreement(UseAgreement.valueOf(useAgreement))
                        .build()
        );
        return "성공적으로 회원가입되었습니다!";
    }

    @Transactional(readOnly = true)
    public boolean existsByFubyId(String fubyId) {
        return memberRepository.existsByFubyId(fubyId);
    }
}
