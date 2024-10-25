<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.container {
	width: 70%;
	margin: auto;
}
</style>
<div class="container">
	<form action="loginEnd.do" method="post">
		<table>
			<tr>
				<th colspan="2"><h1>login-로그인</h1></th>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="userId" id="userId"
					placeholder="ID"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="userPw" id="userPw"
					placeholder="Password"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center">
				<button>로그인</button>
				</td>
			</tr>
		</table>
	</form>
</div>