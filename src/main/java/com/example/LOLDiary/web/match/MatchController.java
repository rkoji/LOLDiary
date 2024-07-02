package com.example.LOLDiary.web.match;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.web.match.dto.MatchDetailDto.ParticipantDto;
import com.example.LOLDiary.web.match.impl.SearchDateDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import static com.example.LOLDiary.web.session.SessionConst.LOGIN_MEMBER;

@Tag(name = "게임 플레이 정보 컨트롤러",description = "게임 플레이 정보를 조회하는 API입니다.")
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    @Operation(summary = "날짜 조회 화면",description = "사용자가 날짜를 선택해 플레이 기록을 조회합니다.")
    @GetMapping("/list")
    public String searchMatchListByDate(@Validated @ModelAttribute("match") SearchDateDto searchDateDto) {
        return "/match/searchMatchForm";
    }

    @Operation(summary = "게임 플레이 리스트" ,description = "사용자가 선택한 날짜의 게임 플레이 리스트를 불러옵니다." )
    @PostMapping("/list")
    public Mono<String> getMatchList(@Validated @ModelAttribute("match") SearchDateDto dto, BindingResult bindingResult
            , HttpServletRequest request, Model model) throws JsonProcessingException {
        Member loginMember = getMemberFromSession(request);
        String nickname = loginMember.getNickname();
        String tag = loginMember.getTag();

        if (bindingResult.hasErrors()) {
            log.info("에러발생");
            log.error(bindingResult.getAllErrors().toString());
            return Mono.just("/match/searchMatchForm");
        }

        return matchService.searchMatchListByDate(dto.getStart(), dto.getEnd(), nickname, tag)
                .doOnNext(matchDtoList -> {
                    for (ParticipantDto participantDto : matchDtoList) {
                        if (participantDto.getChallenges() == null) {
                            log.warn("KDA value is null for summonerName: {}", participantDto.getRiotIdGameName());
                        }
                    }
                    model.addAttribute("matchList", matchDtoList);
                })
                .then(Mono.just("match/searchMatchListForm"));
    }

    @Operation(summary = "사용자 정보 조회",description = "로그인한 사용자의 게임내 사용자 정보를 불러옵니다.")
    @GetMapping("/summoner")
    public Mono<String> getSummoner(HttpServletRequest request, Model model) {
        Member loginMember = getMemberFromSession(request);
        String nickname = loginMember.getNickname();
        String tag = loginMember.getTag();
        model.addAttribute("nickname", nickname);
        return matchService.getSummonerData(nickname, tag)
                .doOnNext(summoner -> model.addAttribute("summoner", summoner))
                .then(Mono.just("match/summoner"));
    }

    private Member getMemberFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (Member) session.getAttribute(LOGIN_MEMBER);
    }

}
