package com.kosmo.user.controller;

import com.kosmo.user.domain.MemberDTO;
import com.kosmo.user.exception.NoMemberException;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String loginForm(){
        return "member/login";
    }

    @PostMapping("/login")
    public String loginProcess(MemberDTO tmpUser, HttpSession ses)
    throws NoMemberException {

        log.info("tmpUser: {}", tmpUser);
        //1. 유효성 체크(userId, userPw) 빈 문자열 체크
        //"/login"으로 redirect 이동

        //2. userService의 loginCheck(tmpUser) 호출
        //회원이 맞다면 => MemberDTO 반환
        //세션에 MemberDTO 객체를 저장 . "loginUser" 키값으로 저장
        // 아니라면 ==> NoMemberException을 발생

        return "redirect:main";//홈으로 이동
    }
}
