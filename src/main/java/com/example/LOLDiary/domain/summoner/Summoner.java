package com.example.LOLDiary.domain.summoner;

import com.example.LOLDiary.web.config.LocalDateTimeToLocalDateDeserializer;
import com.example.LOLDiary.web.member.dto.SummonerDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Summoner {

    @Id
    private String id;

    private String accountId;

    private String puuId;

    private String name;

    private Long profileIconId;

    @JsonDeserialize(using = LocalDateTimeToLocalDateDeserializer.class)
    private LocalDate revisionDate;

    private Long summonerLevel;

    public Summoner saveSummoner(SummonerDto dto) {
        return Summoner.builder()
                .accountId(dto.getAccountId())
                .puuId(dto.getPuuid())
                .name(dto.getName())
                .profileIconId(dto.getProfileIconId())
                .revisionDate(dto.getRevisionDate())
                .summonerLevel(dto.getSummonerLevel())
                .build();
    }

}
