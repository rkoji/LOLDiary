package com.example.LOLDiary.web.diary.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryDto {

    @Schema(description = "챔피언 이름",example = "이즈리얼")
    private String championName;

    @Schema(description = "KDA",example = "3.67")
    private float kda;

    @Schema(description = "사용자 닉네임",example = "닉네임")
    private String name;

    @Schema(description = "일기 내용",example = "오늘 이즈리얼판은 잘했다.")
    private String diaryText;
}
