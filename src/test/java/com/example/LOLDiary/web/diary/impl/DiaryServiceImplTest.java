package com.example.LOLDiary.web.diary.impl;

import com.example.LOLDiary.domain.diary.Diary;
import com.example.LOLDiary.domain.diary.DiaryRepository;
import com.example.LOLDiary.exception.CustomException;
import com.example.LOLDiary.exception.ErrorCode;
import com.example.LOLDiary.web.diary.dto.DiaryDto;
import com.example.LOLDiary.web.diary.dto.DiaryResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class DiaryServiceImplTest {

    @InjectMocks
    private DiaryServiceImpl diaryService;

    @Mock
    private DiaryRepository diaryRepository;

    @DisplayName("다이어리 작성 성공")
    @Test
    void createDiarySuccess() {
        // given
        DiaryDto dto = dto();
        Diary diary = Diary.createDiary(dto);

        when(diaryRepository.save(any(Diary.class))).thenReturn(diary);

        // when
        DiaryResponseDto response = diaryService.createDiary(dto);

        // then
        assertNotNull(response);
    }

    @DisplayName("다이어리 작성 실패 : 다이어리 내용이 없음")
    @Test
    void createDiaryFail() {
        // given
        DiaryDto diaryDto = DiaryDto.builder()
                .championName("이즈리얼")
                .kda(3.67F)
                .name("닉네임")
                .build();

        // when
        CustomException exception = assertThrows(CustomException.class, () -> {
            diaryService.createDiary(diaryDto);
        });

        // then
        assertEquals(ErrorCode.DIARY_TEXT_NOT_FOUND, exception.getErrorCode());

    }


    private DiaryDto dto() {
        return DiaryDto.builder()
                .championName("이즈리얼")
                .kda(3.67F)
                .name("닉네임")
                .diaryText("오늘 이즈리얼 잘했다.")
                .build();

    }

}