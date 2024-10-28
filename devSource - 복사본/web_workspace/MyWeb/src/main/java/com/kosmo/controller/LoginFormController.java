package com.kosmo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosmo.exception.NoMemberException;
import com.kosmo.model.MemberDAO;
import com.kosmo.model.MemberDTO;
/*
 *GET /login.do ==> 로그인 폼을 보여주기
 *POST /login.do ==> 로그인 로직 처리(userId, userPw 받아서 회원정보 맞는지 체크) 
 * */

@WebServlet("/login.do")
public class LoginFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher disp = req.getRequestDispatcher("/member/login.jsp");
		disp.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//1. 사용자가 입력한 아이디, 비번 받기
		String userId = req.getParameter("userId");
		String userPw = req.getParameter("userPw");
		
		//2. 유효성 체크
		if ( userId == null || userPw == null || userId.trim().isBlank() || userPw.trim().isEmpty()) {
			res.sendRedirect("login.do");
			return;
		}
		
		//3. 1번에서 받은 값을 DTO에 담기 ==> tmpDTO
		MemberDTO tmpDTO = new MemberDTO();
		tmpDTO.setUserId(userId);
		tmpDTO.setUserPw(userPw);
		
		//4. MemberDAO의 loginCheck(tmpDTO)
		//==> 아이디, 비번이 일치하면 MemberDTO객체를 반환 ==> loginUser
		//==> 일치하지 않으면 사용자 정의 예외를 발생시키자
		MemberDAO dao = new MemberDAO();
		try {
		MemberDTO loginUser = dao.loginCheck(tmpDTO);
		
		//5. loginUser가 null이 아니라면 ==> index.html로 redierct 이동
		if (loginUser != null) {
			//6. 세션에 loginUser 정보 저장
			//http 프로토콜의 특징: stateless(무상태)
			//상태정보를 유지하기 위해 Session / Cookie 라는 기술을 사용한다
			//Session: 서버쪽에 상태 정보 저장
			//Cookie: 클라이언트 쪽에 상태 정보를 저장
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", loginUser);
			
			res.sendRedirect("index.do");
		}
		
		} catch(NoMemberException e) {
			String msg = e.getMessage();
			String loc ="javascript: history.back()";
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
			RequestDispatcher disp = req.getRequestDispatcher("message.jsp");
			disp.forward(req, res);
		}
	}
}
