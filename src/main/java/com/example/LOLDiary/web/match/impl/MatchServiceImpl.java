package com.example.LOLDiary.web.match.impl;

import com.example.LOLDiary.domain.summoner.Summoner;
import com.example.LOLDiary.web.match.MatchService;
import com.example.LOLDiary.web.match.dto.MatchDetailDto;
import com.example.LOLDiary.web.match.dto.MatchDto;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.LOLDiary.web.match.dto.MatchDetailDto.InfoDto;
import static com.example.LOLDiary.web.match.dto.MatchDetailDto.ParticipantDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

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

    @Override
    public Mono<List<ParticipantDto>> searchMatchListByDate(LocalDateTime start, LocalDateTime end, String summonerName, String tag) {
        log.debug("searchMatchListByDate called with start: {}, end: {}, summonerName: {}, tag: {}", start, end, summonerName, tag);
        return getMatchListByDate(start, end, summonerName, tag)
                .flatMapMany(Flux::fromIterable)
                .flatMap(matchDto -> getMatchDetails(matchDto.getMatchId())
                        .doOnNext(matchDetailDto -> log.debug("Fetched match detail: {}", matchDetailDto))
                        .onErrorResume(error -> {
                            log.error("Error occurred while fetching match details: {}", error.getMessage());
                            return Mono.empty();
                        })
                        .map(matchDetailDto -> filterMatchBySummoner(matchDetailDto, summonerName)))
                .flatMap(matchDetailDto -> {
                    if (matchDetailDto != null) {
                        log.info("Filtered participants: {}", matchDetailDto.getInfo().getParticipants());
                        return Flux.fromIterable(matchDetailDto.getInfo().getParticipants());
                    } else {
                        log.warn("Filtered match detail DTO is null");
                        return Flux.empty();
                    }
                })
                .collectList()
                .doOnNext(participants -> log.debug("Collected participants: {}", participants))
                .onErrorResume(error -> {
                    log.error("Error occurred while processing match list: {}", error.getMessage());
                    return Mono.just(Collections.emptyList());
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

    public Mono<List<MatchDto>> getMatchListByDate(LocalDateTime start, LocalDateTime end, String summonerName, String tag) {
        String baseUrl = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/";

        long startEpochTimestamp = start.atZone(ZoneOffset.UTC).toEpochSecond();
        long endEpochTimestamp = end.atZone(ZoneOffset.UTC).toEpochSecond();

        return getPuuId(summonerName, tag)
                .flatMap(ppuId -> {
                    return webClient.get()
                            .uri(baseUrl + ppuId + "/ids?startTime=" + startEpochTimestamp +
                                    "&endTime=" + endEpochTimestamp)
                            .retrieve()
                            .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                            .doOnNext(matchIds->log.info("Fetched match IDs: {}", matchIds))
                            .map(mathIds -> mathIds.stream()
                                    .map(MatchDto::new)
                                    .collect(Collectors.toList()));
                });
    }

    public Mono<MatchDetailDto> getMatchDetails(String matchId) {
        String baseUrl = "https://asia.api.riotgames.com/lol/match/v5/matches/";
        log.debug("Fetching match details for matchId: {}", matchId);
        return webClient.get()
                .uri(baseUrl + matchId)
                .exchangeToMono(response -> {
                    log.info("Response status code for match ID {}: {}", matchId, response.statusCode());
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(MatchDetailDto.class);
                    } else {
                        log.error("Failed to fetch match details for match ID {}: HTTP {}", matchId, response.statusCode());
                        return Mono.empty();
                    }
                })
                .doOnNext(matchDetailDto -> log.debug("Fetched match details: {}", matchDetailDto))
                .onErrorResume(error -> {
                    log.error("Error occurred while fetching match details: {}", error.getMessage());
                    return Mono.empty();
                });
    }

    private MatchDetailDto filterMatchBySummoner(MatchDetailDto matchDetailDto, String summonerName) {
        log.debug("Filtering match details for summoner: {}", summonerName);
        List<ParticipantDto> participants = matchDetailDto.getInfo().getParticipants();

        // 각 Participant의 summonerName을 로그로 출력
        participants.forEach(participant -> log.debug("Participant summoner name: {}", participant.getRiotIdGameName()));

        // summonerName을 기준으로 필터링
        List<ParticipantDto> filteredParticipants = participants.stream()
                .filter(participant -> participant.getRiotIdGameName().trim().equalsIgnoreCase(summonerName.trim()))
                .collect(Collectors.toList());

        log.debug("Filtered participants: {}", filteredParticipants);
        return new MatchDetailDto(matchDetailDto.getMetadata(), new InfoDto(filteredParticipants));
    }

}


