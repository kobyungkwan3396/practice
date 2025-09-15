package com.ohgiraffers.auth.controller;

import com.ohgiraffers.auth.model.dto.LoginDTO;
import com.ohgiraffers.auth.model.service.AuthService;
import com.ohgiraffers.member.model.dto.MemberDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 로그인 페이지 (GET)
    @GetMapping("/login")
    public String loginPage() {
        return "login/Login";  // templates/login/Login.html
    }

    // 로그인 처리 (POST)
    @PostMapping("/login")
    public String login(LoginDTO loginDTO, HttpSession session) {
        MemberDTO member = authService.login(loginDTO.getId(), loginDTO.getPw());

        if (member != null) {
            // 세션에 로그인 정보 저장
            session.setAttribute("loginMember", member);

            // 세션 유지시간 30분 설정
            session.setMaxInactiveInterval(1 * 60);

            // 세션 유지시간을 세션에 저장 (main 페이지에서 꺼내 사용)
            session.setAttribute("sessionTimeout", session.getMaxInactiveInterval());

            return "redirect:/main";
        }

        // 로그인 실패 시 ?error를 붙여 로그인 페이지로 리다이렉트
        return "redirect:/auth/login?error";
    }

    // 로그아웃 처리 (GET)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
