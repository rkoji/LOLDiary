package com.example.LOLDiary.web.login;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.web.session.SessionConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "로그인, 로그아웃 컨트롤러",description = "로그인, 로그아웃 API 입니다.")
@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @Operation(summary = "로그인 화면", description = "사용자가 로그인 정보를 입력합니다.")
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("login") LoginDto dto) {
        return "login/loginForm";
    }

    @Operation(summary = "로그인", description = "사용자가 로그인 요청을 합니다.")
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("login") LoginDto dto, BindingResult bindingResult,
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }


        Member loginMember = loginService.login(dto.getLoginId(), dto.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        // session 30분 동안 유지
        session.setMaxInactiveInterval(1800);

        return "redirect:/";
    }

    @Operation(summary = "로그아웃",description = "사용자가 로그아웃을 요청합니다.")
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
