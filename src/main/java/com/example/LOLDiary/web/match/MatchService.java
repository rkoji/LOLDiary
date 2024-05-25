package com.example.LOLDiary.web.match;

import com.example.LOLDiary.domain.summoner.Summoner;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public interface MatchService {
    MatchDto searchMatchListByDate(LocalDateTime start, LocalDateTime end, String nickname) throws JsonProcessingException;

    Mono<Summoner> getSummonerData(String summonerName, String tag);
}
