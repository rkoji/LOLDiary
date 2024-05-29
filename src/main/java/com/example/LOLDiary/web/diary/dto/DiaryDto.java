package com.example.LOLDiary.web.diary.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryDto {
    private String championName;
    private float kda;
    private String name;
    private String diaryText;
}
