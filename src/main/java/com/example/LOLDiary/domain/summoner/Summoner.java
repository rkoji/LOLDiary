package com.example.LOLDiary.domain.summoner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Summoner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String summonerId;
    private String accountId;
    private String puuId;
    private String name;
    private int profileIconId;
    private int revisionDate;
    private int summonerLevel;

}
