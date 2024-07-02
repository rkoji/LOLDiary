package com.example.LOLDiary.web.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDto {

    @Schema(description = "사용자 ID",example = "login123")
    private String loginId;

    @Schema(description = "사용자 비밀번호",example = "1234!")
    private String password;
}
