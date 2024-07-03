package com.example.LOLDiary.web.diary.impl;

import com.example.LOLDiary.domain.diary.Diary;
import com.example.LOLDiary.domain.diary.DiaryRepository;
import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.exception.CustomException;
import com.example.LOLDiary.exception.ErrorCode;
import com.example.LOLDiary.web.diary.dto.DiaryDto;
import com.example.LOLDiary.web.diary.DiaryService;
import com.example.LOLDiary.web.diary.dto.DiaryListDto;
import com.example.LOLDiary.web.diary.dto.DiaryResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.LOLDiary.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    @Override
    public DiaryResponseDto createDiary(DiaryDto dto) {
        if (dto.getDiaryText() == null) {
            throw new CustomException(DIARY_TEXT_NOT_FOUND);
        }
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
    public DiaryListDto checkDiaryList(Member member,Long id) {
        Diary diary = diaryRepository.findAllById(id).orElseThrow();
        diaryRepository.findAllById(id).orElseThrow();
        return new DiaryListDto(diary);
    }

    @Override
    @Transactional
    public DiaryResponseDto editDiary(Long id,String diaryText) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new CustomException(DIARY_NOT_FOUND));
        Diary updateDiary = Diary.updateDiary(diaryText,diary);
        diaryRepository.save(updateDiary);
        return new DiaryResponseDto(updateDiary);
    }

}


