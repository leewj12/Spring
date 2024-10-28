<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.container {
	width: 500px;
	height: 350px;
	/* background: beige; */
	margin: 1em auto;
	padding: 1em;
}
</style>

<script>
	//client쪽에서 유효성 체크 ==> javascript
	//form이름.input이름.value ==> 사용자가 입력한 값
	//document.getElementById('아이디명').value ==> 사용자가 입력한 값
	//document.querySelector('#아이디명').value ==> 사용자가 입력한 값	
	function check(){
		if (!frm.userId.value){
			alert('아이디를 입력하세요');
			frm.userId.focus();
			return false;
			
		}
		if(!frm.userPw.value){
			alert('비밀번호를 입력하세요');
			frm.userPw.focus();
			return false;
		}
		return true;
	}
</script>

<div class="container">
	<!-- <form action="loginEnd.do" method="post"> -->
	<form name = "frm" action="login.do" method="post" onsubmit = "return check()">
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
				<button type = "submit">로그인</button>
				<button type = "reset">다시쓰기</button>
				</td>
			</tr>
		</table>
	</form>
</div>