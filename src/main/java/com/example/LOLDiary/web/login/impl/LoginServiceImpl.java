package com.example.LOLDiary.web.login.impl;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.domain.member.MemberRepository;
import com.example.LOLDiary.web.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;

    @Override
    public Member login(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId).orElse(null);
        if (!member.getPassword().equals(password)) {
            return null;
        }
        return member;
    }
}
