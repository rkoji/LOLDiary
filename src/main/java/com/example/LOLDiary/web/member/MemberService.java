package com.example.LOLDiary.web.member;

import com.example.LOLDiary.domain.member.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    void saveMember(JoinMemberDto dto);
}
