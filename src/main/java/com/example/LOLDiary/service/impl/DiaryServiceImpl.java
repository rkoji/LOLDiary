package com.example.LOLDiary.service.impl;

import com.example.LOLDiary.domain.summoner.Summoner;
import com.example.LOLDiary.service.DiaryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DiaryServiceImpl implements DiaryService {

    private String apiKey;

    @Override
    public void createDiary(LocalDate date, String text) {

    }

//    private Summoner getSummonerString(){
//        String apiUrl = "";
//    }
}
