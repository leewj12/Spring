<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title></title>
        <style>
            .error{
                color:red;
            }
        </style>
    </head>    
    <body>
    <div class="wrap">
        <h1 class="error">Error 발생</h1>
        <h3 class="error">${msg}</h3>
    </div>
    </body>
</html>