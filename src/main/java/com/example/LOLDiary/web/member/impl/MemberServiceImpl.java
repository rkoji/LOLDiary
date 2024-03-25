package com.example.LOLDiary.web.member.impl;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.domain.member.MemberRepository;
import com.example.LOLDiary.web.member.JoinMemberDto;
import com.example.LOLDiary.web.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void saveMember(JoinMemberDto dto) {
        log.info("저장 기능 수행");
        Member memberBuilder = Member.builder()
                .loginId(dto.getLoginId())
                .nickname(dto.getNickname())
                .password(dto.getPassword())
                .build();
        memberRepository.save(memberBuilder);
    }
}
