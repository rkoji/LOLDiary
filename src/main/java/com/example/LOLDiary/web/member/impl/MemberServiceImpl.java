package com.example.LOLDiary.web.member.impl;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.domain.member.MemberRepository;
import com.example.LOLDiary.web.member.dto.JoinRequestDto;
import com.example.LOLDiary.web.member.MemberService;
import com.example.LOLDiary.web.member.dto.JoinResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public JoinResponseDto saveMember(JoinRequestDto dto) {
        Member joinMember = memberRepository.save(dto.toEntity());
        return JoinResponseDto.saveJoinResponseDto(joinMember);
    }
}
