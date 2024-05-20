package com.example.LOLDiary.web.member.dto;

import com.example.LOLDiary.domain.member.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class JoinRequestDto {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password;

    public Member toEntity(){
        return Member.builder()
                .loginId(loginId)
                .nickname(nickname)
                .password(password)
                .build();
    }
}
