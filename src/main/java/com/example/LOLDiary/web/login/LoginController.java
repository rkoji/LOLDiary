package com.example.LOLDiary.web.login;

import com.example.LOLDiary.domain.member.Member;
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
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    public static final String LOGIN_MEMBER = "loginMember";

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("login") LoginDto dto) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("login") LoginDto dto, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }


        Member loginMember = loginService.login(dto.getLoginId(), dto.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지않습니다.");
            return "login/loginForm";
        }

        // 세션을 생성하기 전 기존 세션 파기
        request.getSession().invalidate();
        HttpSession session = request.getSession(true);
        session.setAttribute(LOGIN_MEMBER, loginMember);
        // session 30분 동안 유지
        session.setMaxInactiveInterval(1800);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
