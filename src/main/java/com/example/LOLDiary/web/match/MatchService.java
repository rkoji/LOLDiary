package com.example.LOLDiary.web.match;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public interface MatchService {
    MatchDto searchMatchListByDate(LocalDateTime start, LocalDateTime end, String nickname) throws JsonProcessingException;
}
