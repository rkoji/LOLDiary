package com.example.LOLDiary.web.diary;

import com.example.LOLDiary.web.diary.dto.DiaryDto;
import com.example.LOLDiary.web.diary.dto.DiaryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        System.out.println("Champion Name: " + diaryDto.getChampionName());
        System.out.println("KDA: " + diaryDto.getKda());
        System.out.println("Name: " + diaryDto.getName());

        model.addAttribute("diaryDto", diaryDto);
        return "diary/createForm";
    }

    @PostMapping
    public String createDiary(@ModelAttribute("diaryDto") DiaryDto dto,
                              Model model) {
        System.out.println("Champion Name: " + dto.getChampionName());
        System.out.println("KDA: " + dto.getKda());
        System.out.println("Name: " + dto.getName());
        System.out.println("Diary Text: " + dto.getDiaryText());

        DiaryResponseDto diary = diaryService.createDiary(dto);
        model.addAttribute("diary", diary);
        return "diary/confirmation";
    }

}
