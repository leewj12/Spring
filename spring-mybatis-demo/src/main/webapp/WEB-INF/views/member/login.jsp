<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class = "login-wrap">
    <h2>Login</h2>
    <form name = "loginF" action = "login" method ="post">
    <table>
        <tr>
            <td>아이디</td>
            <td>
                <input type = "text" name ="userId"
                id = "userId" placeholder = "ID" required>
            </td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td>
                <input type = "password" name ="userPw"
                id = "userPw" placeholder = "Password" required>
            </td>
        </tr>
            <td colspan = "2" style = "width: 100%; padding: 10px; text-align: center">
                <label for = "saveId">
                    <input type = "checkbox" name = "saveId" id ="saveId">
                    아이디 저장
                </label>
                <button>Login</button>
            </td>
        <tr>
        </tr>
    </table>
    </form>
</div>