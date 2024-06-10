package com.example.LOLDiary.web.diary;

import com.example.LOLDiary.domain.diary.Diary;
import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.web.diary.dto.DiaryDto;
import com.example.LOLDiary.web.diary.dto.DiaryResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.LOLDiary.web.session.SessionConst.LOGIN_MEMBER;

@RequiredArgsConstructor
@Controller
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping
    public String createForm(@RequestParam("championName") String championName,
                             @RequestParam("kda") float kda,
                             @RequestParam("name") String name,
                             Model model) {

        DiaryDto diaryDto = DiaryDto.builder()
                .championName(championName)
                .kda(kda)
                .name(name)
                .build();

        model.addAttribute("diaryDto", diaryDto);
        return "diary/createDiary";
    }

    @PostMapping
    public String createDiary(@ModelAttribute("diaryDto") DiaryDto dto,
                              Model model) {
        DiaryResponseDto diary = diaryService.createDiary(dto);
        model.addAttribute("diary", diary);
        return "diary/confirmation";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
//        Member member = getMemberFromSession(request);
        Diary diary = diaryService.findByNickname(id);
        model.addAttribute("diary", diary);
        return "diary/editDiary";
    }

    @PostMapping("/update/{id}")
    public String editDiary(@PathVariable("id") Long id, @RequestParam String diaryText, Model model) {
        DiaryResponseDto diary = diaryService.editDiary(id, diaryText);
        model.addAttribute("diary", diary);
        return "diary/confirmation";
    }

    @DeleteMapping("/{id}")
    public String deleteDiary(@PathVariable Long id, HttpServletRequest request) {
        Long userId = getMemberFromSession(request).getId();
        diaryService.deleteDiary(id, userId);
        return "loginHome";
    }

    private Member getMemberFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (Member) session.getAttribute(LOGIN_MEMBER);
    }

}
