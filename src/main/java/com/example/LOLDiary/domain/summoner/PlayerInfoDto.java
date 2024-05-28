package com.example.LOLDiary.domain.summoner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PlayerInfoDto {

    private String championName;
    private String teamPosition;
    private double kad;
}
