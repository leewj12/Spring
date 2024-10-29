<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	.container{
		width: 70%;
		margin: auto;
	}
	.container table{
		width: 100%;
		margin: auto;
	}
	th, td{
		padding: 5px;
	}
	input {
		padding: 4px;
		width: 60%;
		border: 1px solid #ddd;
	}
</style>
<div class = "container">
	<form action = "/signup" method = "post">
		<table>
			<tr>
				<th colspan = "2"><h1>Signup -회원가입</h1></th>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type = "text" name = "name" id = "name" placeholder = "Name">
				</td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<input type = "text" name = "userId" id = "userId" placeholder = "ID">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type = "password" name = "userPw" id = "userPw" placeholder = "Password">
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type = "email" name = "email" id = "email" placeholder = "Email">
				</td>
			</tr>
			<tr>
				<td colspan = "2" style= "text-align: center">
					<button>회원가입</button>
					<button type = "reset">다시쓰기</button>
				</td>
			</tr>
		</table>
	</form>
</div>