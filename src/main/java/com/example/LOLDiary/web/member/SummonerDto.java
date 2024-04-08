package com.example.LOLDiary.web.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class SummonerDto {

    private String accountId;
    private Long profileIconId;
    private LocalDate revisionDate;
    private String name;
    private String id;
    private String puuid;
    private Long summonerLevel;

}
