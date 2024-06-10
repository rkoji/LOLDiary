package com.example.LOLDiary.web.diary.impl;

import com.example.LOLDiary.domain.diary.Diary;
import com.example.LOLDiary.domain.diary.DiaryRepository;
import com.example.LOLDiary.web.diary.dto.DiaryDto;
import com.example.LOLDiary.web.diary.DiaryService;
import com.example.LOLDiary.web.diary.dto.DiaryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    @Override
    public DiaryResponseDto createDiary(DiaryDto dto) {
        Diary savedDiary = diaryRepository.save(Diary.createDiary(dto));
        return new DiaryResponseDto(savedDiary);
    }

    @Override
    public Diary findByNickname(Long id) {
        return diaryRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public DiaryResponseDto editDiary(Long id,String diaryText) {
        Diary diary = diaryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 다이어리입니다."));
        Diary updateDiary = Diary.updateDiary(diaryText,diary);
        diaryRepository.save(updateDiary);
        return new DiaryResponseDto(updateDiary);
    }

}


