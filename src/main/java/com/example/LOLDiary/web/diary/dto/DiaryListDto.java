package com.example.LOLDiary.web.diary.dto;

import com.example.LOLDiary.domain.diary.Diary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryListDto {

    private Long id;

    private String name;

    private float kda;

    private String diaryText;

    private LocalDate date;

    public DiaryListDto(Diary diary) {
        this.id = diary.getId();
        this.name = diary.getName();
        this.kda = diary.getKda();
        this.diaryText = diary.getDiaryText();
        this.date = diary.getDate();
    }
}
