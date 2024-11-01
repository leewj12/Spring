<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset = "UTF-8">
        <title></title>
        <style>
            .error{
                color: red;
            }
        </style>
    </head>
    <body>
    <div class = "wrap">
        <h1 class = "error">Error 발생</h1>
        <h3 class = "error">${msg}</h3>
    </div>
    </body>
</html>