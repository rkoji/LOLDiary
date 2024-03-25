package com.example.LOLDiary.web.login;

import com.example.LOLDiary.domain.member.Member;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Member login(String loginId, String password);
}
