<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
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
	input[type = "radio"]{
		width: 20px;
	}
	span.mstate1{
	 color: blue;
	}
	span.mstate0{
	 color: gray;
	}
	span.mstate-1{
	 color: red;
	}
	span.mstate9{
	 color: green;
	}
	span{
		font-weight: bold;
	}
</style>
<c:if test = "${user == null }">
	<<script>
		alert('해당 회원은 존재하지 않아요');
		history.back();
	</script>
</c:if>
<c:if test = "${user != null }">
	<div class = "container">
		<form action = "memberInfo.do" method = "post">
			<table>
				<tr>
					<th colspan = "2"><h1>Member Info</h1></th>
				</tr>
				<tr>
					<th>회원번호</th>
					<td>
						<input type = "text" name = "idx" id = "idx" 
						value = "${user.idx }"
						placeholder = "idx" readonly>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type = "text" name = "name" id = "name" 
						value = "${user.name }"
						placeholder = "Name">
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						<input type = "text" name = "userId" id = "userId" 
						value = "${user.userId }"
						placeholder = "ID">
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type = "email" name = "email" id = "email" 
						value = "${user.email }"
						placeholder = "Email">
					</td>
				</tr>
				<tr>
					<th>회원상태정보</th>
					<td>
						<span class = "mstate${user.mstate }">${user.mstateStr }</span>
						<br><br>
						<label for = "mstate1">
							<input type = "radio" name = "mstate" 
								<c:if test = "${user.mstate == 1}">
									checked
								</c:if>
							id = "mstate1">
							활동 회원
						</label>
						<label for = "mstate2">
							<input type = "radio" name = "mstate" 
								<c:if test = "${user.mstate == 0}">
									checked
								</c:if>
							id = "mstate2">
							정지 회원
						</label>
						<label for = "mstate3">
							<input type = "radio" name = "mstate" 
								<c:if test = "${user.mstate == -1}">
									checked
								</c:if>
							id = "mstate3">
							탈퇴 회원
						</label>
						<label for = "mstate4">
							<input type = "radio" name = "mstate" 
								<c:if test = "${user.mstate == 9}">
									checked
								</c:if>
							id = "mstate4">
							관리자
						</label>
					</td>
				</tr>
				<tr>
					<td colspan = "2" style= "text-align: center">
						<button type = "submit">수정하기</button>
						<button type = "reset">다시쓰기</button>
						<button type = "button"
						onclick = "location.href = 'memberList.do'">회원목록</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</c:if>