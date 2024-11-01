package com.kosmo.common.interceptor;

import com.kosmo.user.domain.MemberDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

//HandlerInterceptor 인터페이스 상속받아 구현하세요
//세션에 저장된 loginUser를 꺼내서 getMstate() 값이 9가 아니라면
//"관리자만 이용 가능합니다", "/main"으로 이동시키기
// 구현 후 WebConfig에 addIntercepter() registry에 등록한다
@Component
public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req,
                             HttpServletResponse res, Object handler)
            throws ServletException, IOException{
        HttpSession ses = req.getSession();
        MemberDTO user = (MemberDTO) ses.getAttribute("loginUser");
        if (user.getMstate() != 9){
            String msg = "관리자만 이용 가능합니다";
            String loc = "/main";

            req.setAttribute("msg", msg);
            req.setAttribute("loc", loc);
            RequestDispatcher dispatcher
                    = req.getRequestDispatcher("/WEB-INF/views/message.jsp");
            dispatcher.forward(req, res);

            return false;
        }

        return true;
    }//-------------------------------
}//class---------------
