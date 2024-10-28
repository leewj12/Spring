package com.kosmo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kosmo.model.MemberDTO;

/*
 * 로그인 여부를 체크하는 필터
 * 특정 url pattern일 때 로그인 여부를 체크하는 필터
 * */

@WebFilter(urlPatterns = {"/admin", "/memberList.do"})
public class AdminCheckFilter extends HttpFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		//loginUser가 있는지 확인		
		MemberDTO user = (MemberDTO) session.getAttribute("loginUser");
		
		if(user == null || user.getMstate() != 9) {
			req.setAttribute("msg",  "관리자가 아닙니다");
			req.setAttribute("loc", "index.do");
			RequestDispatcher disp  = req.getRequestDispatcher("message.jsp");
			disp.forward(req, response);
			//로그인을 하지 않았다면 다음 필터로 가지 못하게 return
			return;
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
