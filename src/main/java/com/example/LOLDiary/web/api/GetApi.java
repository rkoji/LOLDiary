package com.example.LOLDiary.web.api;

import com.example.LOLDiary.web.member.SummonerDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class GetApi {

    @Value("${riot.api}")
    private String apiKey;

    public SummonerDto getSummonerFromApi(String nickname) {
        // riot api에서 summoner 데이터 가져오기
        String summonerData = getSummoner(nickname);
        
        // 받아온 데이터 json 파싱하기
        Map<String, Object> parseSummoner = parseSummoner(summonerData);

        Long epochTime = (Long) parseSummoner.get("revisionDate");
        Date date = new Date(epochTime);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);

        return SummonerDto.builder()
                .id(parseSummoner.get("id").toString())
                .accountId(parseSummoner.get("accountId").toString())
                .puuid(parseSummoner.get("puuid").toString())
                .name(parseSummoner.get("name").toString())
                .profileIconId((Long) parseSummoner.get("profileIconId"))
                .revisionDate(LocalDate.parse(dateString))
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
