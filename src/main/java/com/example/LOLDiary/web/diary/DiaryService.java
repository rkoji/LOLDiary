package com.example.LOLDiary.web.diary;

import com.example.LOLDiary.web.diary.dto.DiaryDto;
import com.example.LOLDiary.web.diary.dto.DiaryResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface DiaryService {
    DiaryResponseDto createDiary(DiaryDto dto);

}
