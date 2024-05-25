package com.example.LOLDiary.web.diary;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.domain.summoner.Summoner;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Controller
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/create")
    public String createFrom(@Validated @ModelAttribute("diary") DiaryDto dto, HttpServletRequest request, Model model) {
        Member loginMember = getMemberFromSession(request);
        String nickname = loginMember.getNickname();
        model.addAttribute("nickname", nickname);
        return "diary/createForm";
    }

    @PostMapping("/create")
    public String createDiary(@Validated @ModelAttribute("diary") DiaryDto dto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = getMemberFromSession(request);
        String nickname = loginMember.getNickname();
        String diaryText = dto.getDiaryText();
        diaryService.createDiary(nickname, diaryText);
        return "redirect:/";
    }

    private Member getMemberFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (Member) session.getAttribute(LOGIN_MEMBER);
    }
}
