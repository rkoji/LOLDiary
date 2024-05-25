package com.example.LOLDiary.web.match;

import com.example.LOLDiary.domain.summoner.Summoner;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface MatchService {
    Mono<List<MatchDto>> searchMatchListByDate(LocalDateTime start, LocalDateTime end, String nickname, String tag) throws JsonProcessingException;

    Mono<Summoner> getSummonerData(String summonerName, String tag);
}
