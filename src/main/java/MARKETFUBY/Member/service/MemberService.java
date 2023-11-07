package MARKETFUBY.Member.service;

import MARKETFUBY.Member.domain.Member;
import MARKETFUBY.Member.domain.RefreshToken;
import MARKETFUBY.Member.domain.Sex;
import MARKETFUBY.Member.domain.UseAgreement;
import MARKETFUBY.Member.dto.MemberLoginResponseDto;
import MARKETFUBY.Member.repository.MemberRepository;
import MARKETFUBY.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    private final RefreshTokenService refreshTokenService;

    // AccessToken 만료 시간을 1시간으로 설정
    private Long AccessExpireTimeMs = 1000 * 60 * 60L;
    // RefreshToken 만료 시간을 7일로 설정
    private Long RefreshExpireTimeMs = 7 * 24 * 1000 * 60 * 60L;

    // application-secret.yml 에서 키값 가져오기
    @Value("${spring.jwt.secret-key}")
    private String accessKey;
    @Value("${spring.jwt.refresh-key}")
    private String refreshKey;

    // 회원가입
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

    // 로그인
    public MemberLoginResponseDto login(String fubyId, String passwd) {
        // 존재하지 않는 fubyId로 로그인을 시도하는 경우
        Member foundMember = findMemberByFubyId(fubyId);

        // 존재하는 fubyId를 입력했지만 잘못된 비밀번호를 입력하는 경우
        if(!encoder.matches(passwd, foundMember.getPasswd())) throw new RuntimeException("잘못된 비밀번호입니다.");

        // 로그인 성공 -> 토큰 생성
        String accessToken = JwtUtil.createAccessToken(foundMember.getFubyId(), accessKey, AccessExpireTimeMs);
        String refreshToken = JwtUtil.createRefreshToken(foundMember.getFubyId(), refreshKey, RefreshExpireTimeMs);

        // RefreshToken을 DB에 저장
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setValue(refreshToken);
        refreshTokenEntity.setMemberId(foundMember.getMemberId());
        refreshTokenService.addRefreshToken(refreshTokenEntity);

        return MemberLoginResponseDto.builder()
                .memberId(foundMember.getMemberId())
                .fubyId(foundMember.getFubyId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // 회원탈퇴
    public String delete(Long memberId, Authentication authentication) {
        Member member = findMemberById(memberId);
        memberRepository.delete(member);
        return "성공적으로 탈퇴되었습니다!";
    }

    // fubyId로 회원 찾기
    @Transactional(readOnly = true)
    public Member findMemberByFubyId(String fubyId) {
        return memberRepository.findByFubyId(fubyId)
                .orElseThrow(() -> new EntityNotFoundException(fubyId + "은 존재하지 않는 아이디입니다."));
    }

    // memberId로 Member 정보 찾기
    @Transactional(readOnly = true)
    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member ID가 " + id + "인 회원이 존재하지 않습니다."));
    }

    // 새로운 AccessToken 발급받기
    public MemberLoginResponseDto requestRefresh(String refreshToken) {
        // 해당 RefreshToken이 유효한지 DB에서 탐색
        RefreshToken foundRefreshToken = refreshTokenService.findRefreshToken(refreshToken);
        // RefreshToken에 들어있는 username 값 가져오기
        Claims claims = JwtUtil.parseRefreshToken(foundRefreshToken.getValue(), refreshKey);
        String fubyId = claims.get("username").toString();
        System.out.println("Username found in RefreshToken: " + fubyId);
        // 가져온 fubyId에 해당하는 회원이 존재하는지 확인
        Member member = findMemberByFubyId(fubyId);

        // 새 AccessKey 생성
        String accessToken = JwtUtil.createAccessToken(member.getFubyId(), accessKey, AccessExpireTimeMs);
        // 새 AccessKey와 기존 RefreshKey를 DTO에 담아 리턴
        return MemberLoginResponseDto
                .builder()
                .memberId(member.getMemberId())
                .fubyId(member.getFubyId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
