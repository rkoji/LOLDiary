package com.example.LOLDiary.web.match.impl;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SearchDateDto {

    @Schema(description = "조회 시작 날짜", example = "2024-07-02")
    private LocalDateTime start;
    @Schema(description = "조회 마지막 날짜", example = "2024-07-03")
    private LocalDateTime end;

}
