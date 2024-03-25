package com.example.LOLDiary.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface DiaryService {


    void createDiary(LocalDate date, String text);

}
