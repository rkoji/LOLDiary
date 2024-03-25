package com.example.LOLDiary.web.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@AllArgsConstructor
public class JoinMemberDto {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password;
}
