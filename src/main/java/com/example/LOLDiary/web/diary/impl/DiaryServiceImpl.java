package com.example.LOLDiary.web.diary.impl;

import com.example.LOLDiary.domain.diary.Diary;
import com.example.LOLDiary.domain.diary.DiaryRepository;
import com.example.LOLDiary.web.diary.dto.DiaryDto;
import com.example.LOLDiary.web.diary.DiaryService;
import com.example.LOLDiary.web.diary.dto.DiaryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    @Override
    public DiaryResponseDto createDiary(DiaryDto dto) {
        Diary savedDiary = diaryRepository.save(Diary.createDiary(dto));
        return new DiaryResponseDto(savedDiary);
    }
}


