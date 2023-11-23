package MARKETFUBY.Member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class MemberJoinRequestDto {
    @NotBlank(message="아이디를 입력해 주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Size(min = 6, max = 16, message = "6자 이상 16자 이하의 영문, 숫자를 조합")
    private String fubyId;

    @NotBlank(message="비밀번호를 입력해 주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{10,}$")
    private String passwd;

    @NotBlank(message="이름을 입력해 주세요.")
    private String name;

    @NotBlank(message="이메일을 입력해 주세요.")
    @Email(message = "이메일 형식으로 입력해 주세요.")
    @Pattern(regexp = "[a-zA-z0-9]+@[a-zA-z]+[.]+[a-zA-z.]+")
    private String email;

    @NotBlank(message = "휴대폰 번호를 입력해 주세요.")
    @Pattern(regexp = "^[0-9]*$")
    private String phone;

    private String home;

    private String sex;
    private String birthday;
}