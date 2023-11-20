package MARKETFUBY.Member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberJoinRequestDto {
    private String fubyId;
    private String passwd;
    private String name;
    private String email;
    private String phone;
    private String home;
    private String sex;
    private String birthday;
}
