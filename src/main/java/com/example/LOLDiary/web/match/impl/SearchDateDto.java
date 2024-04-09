package com.example.LOLDiary.web.match.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SearchDateDto {

    private LocalDateTime start;
    private LocalDateTime end;

}
