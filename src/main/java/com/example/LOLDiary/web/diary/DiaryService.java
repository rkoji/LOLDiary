package com.example.LOLDiary.web.diary;

import com.example.LOLDiary.domain.diary.Diary;
import com.example.LOLDiary.web.diary.dto.DiaryDto;
import com.example.LOLDiary.web.diary.dto.DiaryResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface DiaryService {
    DiaryResponseDto createDiary(DiaryDto dto);

    DiaryResponseDto editDiary(Long id, String diaryText);

    Diary findByNickname(Long nickname);

    void deleteDiary(Long id);
}
