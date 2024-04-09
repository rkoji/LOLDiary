package com.example.LOLDiary.web.match.impl;

import com.example.LOLDiary.domain.summoner.Summoner;
import com.example.LOLDiary.domain.summoner.SummonerRepository;
import com.example.LOLDiary.web.api.GetApi;
import com.example.LOLDiary.web.match.MatchDto;
import com.example.LOLDiary.web.match.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final SummonerRepository summonerRepository;
    private final GetApi getApi;


    @Override
    public MatchDto searchMatchListByDate(LocalDateTime start, LocalDateTime end, String nickname) throws JsonProcessingException {
        Summoner summoner = summonerRepository.findByName(nickname).orElseThrow(IllegalAccessError::new);

        String puuId = summoner.getPuuId();

        long startEpochTimestamp  = start.atZone(ZoneOffset.UTC).toEpochSecond();
        long endEpochTimestamp  = end.atZone(ZoneOffset.UTC).toEpochSecond();

        List<String> matchList = getApi.getMatchList(startEpochTimestamp, endEpochTimestamp, puuId);

        return MatchDto.builder()
                .matchIds(matchList)
                .build();
    }
}
