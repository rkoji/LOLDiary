package com.example.LOLDiary.web.member.dto;

import com.example.LOLDiary.domain.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class JoinRequestDto {

    @NotEmpty
    @Schema(description = "사용자 ID",example = "login123")
    private String loginId;

    @NotEmpty
    @Schema(description = "사용자 닉네임",example = "nickname")
    private String nickname;

    @NotEmpty
    @Schema(description = "사용자 비밀번호",example = "1234!")
    private String password;

    @NotEmpty
    @Schema(description = "사용자 태그",example = "KR1")
    private String tag;

    public Member toEntity(){
        return Member.builder()
                .loginId(loginId)
                .nickname(nickname)
                .password(password)
                .tag(tag)
                .build();
    }
}
