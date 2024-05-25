package com.example.LOLDiary.web.match.impl;

import com.example.LOLDiary.domain.summoner.Summoner;
import com.example.LOLDiary.domain.summoner.SummonerRepository;
import com.example.LOLDiary.web.match.MatchDto;
import com.example.LOLDiary.web.match.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final SummonerRepository summonerRepository;

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<Summoner> getSummonerData(String summonerName, String tag) {
        String baseUrl = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/";
        return getPuuId(summonerName, tag)
                .flatMap(puuId -> {
                    return webClient.get()
                            .uri(baseUrl + puuId)
                            .retrieve()
                            .bodyToMono(Summoner.class);
                });
    }

    private Mono<String> getPuuId(String summonerName, String tag) {
        String baseUrl = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";
        return webClient.get()
                .uri(baseUrl + summonerName + "/" + tag)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonNode -> jsonNode.get("puuid").asText());
    }


    @Override
    public MatchDto searchMatchListByDate(LocalDateTime start, LocalDateTime end, String nickname) throws JsonProcessingException {
        Summoner summoner = summonerRepository.findByName(nickname).orElseThrow(IllegalAccessError::new);

        String puuId = summoner.getPuuId();

        long startEpochTimestamp = start.atZone(ZoneOffset.UTC).toEpochSecond();
        long endEpochTimestamp = end.atZone(ZoneOffset.UTC).toEpochSecond();

////        List<String> matchList = getApi.getMatchList(startEpochTimestamp, endEpochTimestamp, puuId);
//
//        return MatchDto.builder()
//                .matchIds(matchList)
//                .build();
        return null;
    }
}
