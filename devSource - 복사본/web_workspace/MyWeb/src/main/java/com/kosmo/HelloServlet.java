package com.kosmo;

//import javax.servlet.*; // tomcat/lib/servlet-api.jar, jsp-api.jar
//import javax.servlet.http.*; // jakarta.servlet ...식으로 변경
//import java.io.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1. HttpServlet 상속받아야함
//2. 메서드를 오버라이드 (doGet(), doPost(), doDelete(), doPut()...)
//3. 서블릿 등록
//3-1. WEB--INF/web.xml 등록
//3-2. @webServlet 어노테이션 이용
@WebServlet("/hello.do") // 논리적인 url-pattern
public class HelloServlet extends HttpServlet {
	
	public HelloServlet() {
		System.out.println("HelloServlet생성자...");
	}
	@Override
	public void init() {// init() 한번만 호출된다. 초기화 작업을 여기서
		System.out.println("init() 호출됨");
	}
	//service()/doGet()/doPost()... ==>스레드로 실행됨. 요창이 올떄마다 실행
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		System.out.println("doGet() 호출됨...");
		res.setContentType("text/html; charset = utf-8");
		// 응답보맬 웹문서의 컨텐트 타입을 지정
		PrintWriter out = res.getWriter();
		//res ==> 브라우저 ==> 브라우저에 출력할 스트림(PrintWriter)
		out.println("<h1>Hello Servlet</h1>");
		out.println("<h2>안녕 못해 서블릿</h2>");
		//클라이언트가 보낸 요청 데이터를 받아보자 ==> req.get{arameter()로 받는다
		String name = req.getParameter("name");
		out.println("<h2 style = 'color: green'>환영합니다 " + name + "님~</h2>");
		
		out.close();
	}
	@Override
	public void destroy() {//서버가 정상 종료될 떄 딱 한번 호출
		System.out.println("destroy()호출됨...");
	}

}
