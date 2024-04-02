package com.example.LOLDiary.web.diary;

import com.example.LOLDiary.domain.summoner.Summoner;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface DiaryService {
    void createDiary(String nickname, String diaryText);

    Summoner getSummonerData(String nickname);
}
