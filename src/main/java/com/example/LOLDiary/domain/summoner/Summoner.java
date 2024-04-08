package com.example.LOLDiary.domain.summoner;

import com.example.LOLDiary.web.member.SummonerDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String summonerId;
    private String accountId;
    private String puuId;
    private String name;
    private Long profileIconId;
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
