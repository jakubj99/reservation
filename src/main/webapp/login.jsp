<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="/header.jsp" %>
<form action="/login" method="post">

    <div>
        <label for="username">Username:</label>
        <input id="username" name="username" type="text"/>
    </div>
    <div>
        <label for="password">Password:</label>
        <input id="password" name="password" type="password" />
    </div>
    <input type="submit" value="login">
</form>
</body>
</html>
