package com.example.LOLDiary.web.match.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.boot.Metadata;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDetailDto {

    private MetadataDto metadata;
    private InfoDto info;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MetadataDto{
        private String matchId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InfoDto{
        private List<ParticipantDto> participants;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ParticipantDto{
        private String championName;
        private String riotIdGameName;
        private ChallengesDto challenges;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChallengesDto{
        private float kda;
    }
}
