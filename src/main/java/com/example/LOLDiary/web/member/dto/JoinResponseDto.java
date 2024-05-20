package com.example.LOLDiary.web.member.dto;

import com.example.LOLDiary.domain.member.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class JoinResponseDto {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password;

    public static JoinResponseDto saveJoinResponseDto(Member joinMember) {
        return JoinResponseDto.builder()
                .id(joinMember.getId())
                .loginId(joinMember.getLoginId())
                .nickname(joinMember.getNickname())
                .password(joinMember.getPassword())
                .build();
    }

}
