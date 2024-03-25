package com.example.LOLDiary;

import com.example.LOLDiary.domain.member.Member;
import com.example.LOLDiary.web.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.LOLDiary.web.session.SessionConst.*;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    private String home(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        Member loginMember = (Member) session.getAttribute(LOGIN_MEMBER);

        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);

        return "loginHome";
    }
}
