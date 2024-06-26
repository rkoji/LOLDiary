package com.example.LOLDiary.web.member.impl;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.domain.member.MemberRepository;
import com.example.LOLDiary.web.member.dto.JoinRequestDto;
import com.example.LOLDiary.web.member.dto.JoinResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    private MockMvc mockMvc;

    @DisplayName("회원 가입 성공")
    @Test
    void signUpSuccess() throws Exception {
        // given
        JoinRequestDto joinRequestDto = joinMemberDto();
        Member savedMember = Member.builder()
                .id(1L)
                .loginId("abcd")
                .nickname("kiin")
                .password("1234")
                .build();
        // when
        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);

        JoinResponseDto joinResponseDto = memberService.saveMember(joinRequestDto);
        // then
        assertThat(joinResponseDto.getId()).isEqualTo(1L);
        assertThat(joinResponseDto.getNickname()).isEqualTo(joinRequestDto.getNickname());

    }

    private JoinRequestDto joinMemberDto() {
        return JoinRequestDto.builder()
                .loginId("abcd")
                .nickname("kiin")
                .password("1234")
                .build();
    }

}