package com.kosmo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosmo.model.MemberDAO;
import com.kosmo.model.MemberDTO;
//GET /memberInfo.do?idx=10 ==> 회원정보 가져오기 (select)
//POST /memberInfo.do ==> 회원정보 수정하기 (update) 

@WebServlet("/memberInfo.do")
public class MemberEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//1. 회원번호 받기
		String idx = req.getParameter("idx");
		
		//2. 유효성 체크
		if(idx == null || idx.trim().isBlank()) {
			res.sendRedirect("memberList.do");
			return;
		}
		
		//3. MemberDAO의 getMember(idx) 호출 ==> MemberDTO
		MemberDAO dao = new MemberDAO();
		MemberDTO user = dao.getMember(Integer.parseInt(idx.trim()));
		
		//4. req에 MemberDTO 객체 저장
		req.setAttribute("user", user);
		
		//5. member/memberInfo.jsp로 forward 이동
		RequestDispatcher disp = req.getRequestDispatcher("member/memberInfo.jsp");
		disp.forward(req, res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
