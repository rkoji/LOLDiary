package com.example.LOLDiary.web.member;

import com.example.LOLDiary.web.member.dto.JoinRequestDto;
import com.example.LOLDiary.web.member.dto.JoinResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    JoinResponseDto saveMember(JoinRequestDto dto);
}
