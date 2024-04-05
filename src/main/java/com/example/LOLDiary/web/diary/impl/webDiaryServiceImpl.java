package com.example.LOLDiary.web.diary.impl;

import com.example.LOLDiary.domain.diary.Diary;
import com.example.LOLDiary.domain.diary.DiaryRepository;
import com.example.LOLDiary.domain.summoner.Summoner;
import com.example.LOLDiary.domain.summoner.SummonerRepository;
import com.example.LOLDiary.web.api.GetApi;
import com.example.LOLDiary.web.diary.DiaryService;
import com.example.LOLDiary.web.member.SummonerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class webDiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    private final SummonerRepository summonerRepository;
    private final GetApi getApi;


    @Override
    public void createDiary(String nickname, String diaryText) {
        SummonerDto summoner = getApi.getSummonerFromApi(nickname);
        if (summoner.getName().equals(nickname)) {
            Diary diary = Diary.builder()
                    .nickname(nickname)
                    .diaryText(diaryText)
                    .build();
            diaryRepository.save(diary);
        }
    }

    // summoner 데이터가 잘 넘어오는지 확인용
    @Override
    public Summoner getSummonerData(String nickname) {
        SummonerDto summonerFromApi = getApi.getSummonerFromApi(nickname);
        System.out.println("nickname : " + nickname);
        System.out.println("summonerFromApi.getName() = " + summonerFromApi.getName());
        if (summonerFromApi.getName().equals(nickname)) {
            Summoner summoner = new Summoner();
            Summoner summonerData = summoner.saveSummoner(summonerFromApi);
            summonerRepository.save(summonerData);
            return summonerData;
        }
        return null;
    }


}
