package com.example.LOLDiary.web.member.impl;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.domain.member.MemberRepository;
import com.example.LOLDiary.exception.CustomException;
import com.example.LOLDiary.exception.ErrorCode;
import com.example.LOLDiary.web.member.dto.JoinRequestDto;
import com.example.LOLDiary.web.member.dto.JoinResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @DisplayName("회원 가입 성공")
    @Test
    void signUpSuccess() {
        // given
        JoinRequestDto joinRequestDto = joinMemberDto();
        Member savedMember = Member.builder()
                .id(1L)
                .loginId("abcd")
                .nickname("kiin")
                .password("1234")
                .tag("123")
                .build();
        // when
        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);

        JoinResponseDto joinResponseDto = memberService.saveMember(joinRequestDto);
        // then
        assertThat(joinResponseDto.getId()).isEqualTo(1L);
        assertThat(joinResponseDto.getNickname()).isEqualTo(joinRequestDto.getNickname());

    }

    @DisplayName("회원 가입 실패")
    @Test
    void signUpFail() {
        // given
        JoinRequestDto joinRequestDto = joinMemberDto();

        given(memberRepository.findByLoginId(anyString()))
                .willReturn(Optional.of(new Member()));

        // when
        CustomException exception = Assertions.assertThrows(CustomException.class, () -> {
            memberService.saveMember(joinRequestDto);
        });

        // then
        Assertions.assertEquals(ErrorCode.USER_ALREADY_EXISTS, exception.getErrorCode());
        verify(memberRepository).findByLoginId(joinRequestDto.getLoginId());

    }

    private JoinRequestDto joinMemberDto() {
        return JoinRequestDto.builder()
                .loginId("abcd")
                .nickname("kiin")
                .password("1234")
                .tag("123")
                .build();
    }

}