<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Date,java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello</title>
<link rel = "stylesheet" type = "text/css" href = "css/index.css">
</head>
<body>
<h1>HELLO JSP</h1>
<h2>JSP- Java Server Page의 약자, 동적인 처리를 담당합니다</h2>
<hr>
<p>
<% //자바코드는 scriptlet 태그 안에서 작성 %%
for(int i = 0; i < 3; i++){
%>
	<img src = "images/tomcat.png" alt = "톰캣 이미지잆니다">
<% 
	}//for----
	Date today = new Date();
	// out 내장객체: 브라우저에 출력하고자 할 때 사용
	out.println("<h2 class = 'today'>" + today + "</h2>");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String todayStr = sdf.format(today);
%>

<h2 class = "today2"><%= todayStr %></h2>
<!-- HTML주석 -->
<%--jsp주석 (권장) <%= 변수%> 출력식, 변수값을 출력함 out.println(변수);와 동일함 --%>

</p>

</body>
</html>