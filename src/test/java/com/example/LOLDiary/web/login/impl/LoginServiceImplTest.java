package com.example.LOLDiary.web.login.impl;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.domain.member.MemberRepository;
import com.example.LOLDiary.exception.CustomException;
import com.example.LOLDiary.exception.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @InjectMocks
    private LoginServiceImpl loginService;

    @Mock
    private MemberRepository memberRepository;

    @DisplayName("로그인 성공")
    @Test
    void loginSuccess() {
        // given
        Member member = member();

        given(memberRepository.findByLoginId(member.getLoginId()))
                .willReturn(Optional.of(member));
        // when
        Member result = loginService.login(member.getLoginId(), member.getPassword());

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(member.getLoginId(),result.getLoginId());

    }

    @DisplayName("로그인 실패 : 비밀번호가 일치하지 않는 경우")
    @Test
    void loginFail_InvalidPassword(){
        //given
        String loginId = "Id";
        String password = "wrongPassword";
        Member member = member();

        given(memberRepository.findByLoginId(loginId))
                .willReturn(Optional.of(member));

        // when
        CustomException exception = Assertions.assertThrows(CustomException.class, () -> {
            loginService.login(loginId, password);
        });

        // then
        Assertions.assertEquals(ErrorCode.INVALID_PASSWORD, exception.getErrorCode());
        verify(memberRepository).findByLoginId(loginId);

    }

    @DisplayName("로그인 실패 : 존재하지 않는 아이디")
    @Test
    void loginFail_UserNotFound(){
        //given
        String loginId = "wrongId";
        String password = "1234";

        given(memberRepository.findByLoginId(loginId))
                .willReturn(Optional.empty());

        // when
        CustomException exception = Assertions.assertThrows(CustomException.class, () -> {
            loginService.login(loginId, password);
        });

        // then
        Assertions.assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
        verify(memberRepository).findByLoginId(loginId);
    }

    private Member member() {

        return Member.builder()
                .id(1L)
                .loginId("Id")
                .nickname("nickname")
                .password("1234")
                .tag("123")
                .build();
    }


}