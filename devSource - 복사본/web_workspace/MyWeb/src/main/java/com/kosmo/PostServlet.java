package com.kosmo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/post.do")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		//1. 사용자가 입력한 값(userId, userPw)
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("userId");//input name
		String pw = req.getParameter("userPw");
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		//2. 유효성 체크
		if(id == null || pw == null || id.trim().isEmpty() || pw.trim().isEmpty()) {
			out.println("<script>");
			out.println("alert('아이디, 비밀번호를 입력해야 해요')");
			out.println("history.back()");
			out.println("</script>");
			return;
		}
		
		//3. DB관련 로직처리 ==> Model에게 넘긴다

		
		//4. 처리 결과 응답 데이터 생성
		out.println("<h1>아이디: " + id + "</h1>");
		out.println("<h1>비밀번호: " + pw + "</h1>");
		
		out.close();
	}

}
