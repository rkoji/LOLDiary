package com.example.LOLDiary.web.member;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.web.member.dto.JoinRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String joinForm(@ModelAttribute Member member) {
        return "members/joinForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute("member") JoinRequestDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("에러발생");
            log.error(bindingResult.getAllErrors().toString());
            return "members/joinForm";
        }
        memberService.saveMember(dto);
        return "redirect:/";
    }
}
