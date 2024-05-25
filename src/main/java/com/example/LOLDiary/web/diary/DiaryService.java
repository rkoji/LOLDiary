package com.example.LOLDiary.web.diary;

import com.example.LOLDiary.domain.summoner.Summoner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface DiaryService {
    void createDiary(String nickname, String diaryText);

}
