package MARKETFUBY.Member.controller;

import MARKETFUBY.Member.dto.MemberJoinRequestDto;
import MARKETFUBY.Member.dto.MemberLoginRequestDto;
import MARKETFUBY.Member.dto.MemberLoginResponseDto;
import MARKETFUBY.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

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
}
