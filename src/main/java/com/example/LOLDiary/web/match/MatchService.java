package com.example.LOLDiary.web.match;

import com.example.LOLDiary.domain.summoner.Summoner;
import com.example.LOLDiary.web.match.dto.MatchDetailDto;
import com.example.LOLDiary.web.match.dto.MatchDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.LOLDiary.web.match.dto.MatchDetailDto.*;

@Service
public interface MatchService {
    Mono<List<ParticipantDto>> searchMatchListByDate(LocalDateTime start, LocalDateTime end, String nickname, String tag) throws JsonProcessingException;

    Mono<Summoner> getSummonerData(String summonerName, String tag);
}
