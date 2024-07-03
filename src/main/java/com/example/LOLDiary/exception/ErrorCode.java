package com.example.LOLDiary.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_ALREADY_EXISTS(CONFLICT, "이미 존재하는 회원입니다."),
    USER_NOT_FOUND(NOT_FOUND, "존재하지 않는 아이디입니다." ),
    INVALID_PASSWORD(UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    DIARY_TEXT_NOT_FOUND(NOT_FOUND,"다이어리 내용이 존재하지않습니다." ),
    DIARY_NOT_FOUND(NOT_FOUND, "존재하지 않는 다이어리입니다."),;

    private final HttpStatus httpStatus;
    private final String message;
}
