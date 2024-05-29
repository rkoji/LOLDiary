package com.example.LOLDiary.domain.diary;

import com.example.LOLDiary.web.diary.dto.DiaryDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String championName;
    @NotNull
    private float kda;
    @NotEmpty
    private String diaryText;

    private LocalDate date;


    public static Diary createDiary(DiaryDto dto) {
        return Diary.builder()
                .name(dto.getName())
                .championName(dto.getChampionName())
                .kda(dto.getKda())
                .diaryText(dto.getDiaryText())
                .date(LocalDate.now())
                .build();
    }
}
