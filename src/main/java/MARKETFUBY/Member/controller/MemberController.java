package MARKETFUBY.Member.controller;

import MARKETFUBY.Member.dto.MemberJoinRequestDto;
import MARKETFUBY.Member.dto.MemberLoginRequestDto;
import MARKETFUBY.Member.dto.MemberLoginResponseDto;
import MARKETFUBY.Member.dto.RefreshTokenRequestDto;
import MARKETFUBY.Member.service.MemberService;
import MARKETFUBY.Member.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> join (@RequestBody MemberJoinRequestDto requestDto) {
        return ResponseEntity.ok().body(memberService.join(requestDto.getFubyId(), requestDto.getPasswd(), requestDto.getName(), requestDto.getEmail(), requestDto.getPhone(), requestDto.getHome(), requestDto.getSex(), requestDto.getBirthday(), requestDto.getLevel(), requestDto.isSelectAgreement(), requestDto.getUseAgreement()));
    }

    // 로그인
    @PostMapping("/login")
    public MemberLoginResponseDto login (@RequestBody MemberLoginRequestDto requestDto) {
        return memberService.login(requestDto.getFubyId(), requestDto.getPasswd());
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public String logout(@RequestBody RefreshTokenRequestDto requestDto) {
        refreshTokenService.deleteRefreshToken(requestDto.getRefreshToken());
        return "로그아웃되었습니다.";
    }
}
