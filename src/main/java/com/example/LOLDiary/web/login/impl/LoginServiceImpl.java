package com.example.LOLDiary.web.login.impl;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.domain.member.MemberRepository;
import com.example.LOLDiary.exception.CustomException;
import com.example.LOLDiary.exception.ErrorCode;
import com.example.LOLDiary.web.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;

    @Override
    public Member login(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        if (!member.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        return member;
    }
}
