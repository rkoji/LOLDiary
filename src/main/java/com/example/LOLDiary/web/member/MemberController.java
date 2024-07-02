package com.example.LOLDiary.web.member;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.web.member.dto.JoinRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "회원가입 컨트롤러", description = "회원가입 API 입니다.")
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입 화면", description = "사용자가 회원가입 정보를 입력합니다.")
    @GetMapping("/add")
    public String joinForm(@ModelAttribute Member member) {
        return "members/joinForm";
    }

    @Operation(summary = "회원가입",description = "사용자가 회원가입을 요청합니다.")
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
