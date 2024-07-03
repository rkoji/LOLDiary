package com.example.LOLDiary.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_ALREADY_EXISTS(CONFLICT, "이미 존재하는 회원입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
