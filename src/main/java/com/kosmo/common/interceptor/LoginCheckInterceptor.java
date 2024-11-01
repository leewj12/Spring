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

/*Interceptor
* 1. 컨트롤러가 실행되기 전에 사전 처리할 일이 있으면 인터셉터에서 구현한다
* 구현방법
* 1-1. HandlerInterceptor 인터페이스를 상속
* 1-2. HandlerInterceptorAdapter 추상클래스를 상속
* 2. url pattern ==> 어떤 인터셉터를 적용할 것인지 설정
*   WebConfig 클래스에서 설정
* */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    //1. preHandle(): 사전 처리. 컨트롤러 실행 전
    //2. postHandle(): 사후 처리. 컨트롤러 실행 후, 아직 뷰를 실행하기 전
    //3. afterCompletion(): 컨트롤러 실핼하고 뷰도 실행한 후 호출
    @Override
    public boolean preHandle(HttpServletRequest req,
                             HttpServletResponse res, Object handler)
            throws ServletException, IOException {

        HttpSession ses = req.getSession();
        MemberDTO member = (MemberDTO) ses.getAttribute("loginUser");
        if (member == null) {
            req.setAttribute("msg", "로그인 해야 이용할 수 있어요");
            req.setAttribute("loc", "/login");
            RequestDispatcher dispatcher
                    = req.getRequestDispatcher("/WEB-INF/views/message.jsp");
            dispatcher.forward(req, res);
            return false;//false 반환하면 컨트롤러로 넘어가지 못함
        }//if---------------
        return true;
    }

}
