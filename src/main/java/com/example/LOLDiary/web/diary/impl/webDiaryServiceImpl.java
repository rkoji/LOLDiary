package com.example.LOLDiary.web.diary.impl;

import com.example.LOLDiary.domain.diary.Diary;
import com.example.LOLDiary.domain.diary.DiaryRepository;
import com.example.LOLDiary.domain.member.MemberRepository;
import com.example.LOLDiary.domain.summoner.Summoner;
import com.example.LOLDiary.domain.summoner.SummonerRepository;
import com.example.LOLDiary.web.diary.DiaryService;
import com.example.LOLDiary.web.member.SummonerDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class webDiaryServiceImpl implements DiaryService {

    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;
    private final SummonerRepository summonerRepository;


    @Value("${riot.api}")
    private String apiKey;

    @Override
    public void createDiary(String nickname, String diaryText) {
        SummonerDto summoner = getSummonerFromApi(nickname);
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
        SummonerDto summonerFromApi = getSummonerFromApi(nickname);
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

    private SummonerDto getSummonerFromApi(String nickname) {
        // riot api에서 summoner 데이터 가져오기
        String summonerData = getSummoner(nickname);

        // 받아온 데이터 json 파싱하기
        Map<String, Object> parseSummoner = parseSummoner(summonerData);
        return SummonerDto.builder()
                .id(parseSummoner.get("id").toString())
                .accountId(parseSummoner.get("accountId").toString())
                .puuid(parseSummoner.get("puuid").toString())
                .name(parseSummoner.get("name").toString())
                .profileIconId((Long) parseSummoner.get("profileIconId"))
                .revisionDate((Long) parseSummoner.get("revisionDate"))
                .summonerLevel((Long) parseSummoner.get("summonerLevel"))
                .build();
    }


    private String getSummoner(String nickname) {
        String apiUrl = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + nickname + "?api_key=" + apiKey;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            return response.toString();
        } catch (Exception e) {
            return "failed to get response";
        }
    }

    private Map<String, Object> parseSummoner(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("accountId", jsonObject.get("accountId"));
        resultMap.put("profileIconId", jsonObject.get("profileIconId"));
        resultMap.put("revisionDate", jsonObject.get("revisionDate"));
        resultMap.put("name", jsonObject.get("name"));
        resultMap.put("id", jsonObject.get("id"));
        resultMap.put("puuid", jsonObject.get("puuid"));
        resultMap.put("summonerLevel", jsonObject.get("summonerLevel"));
        return resultMap;

    }

}
