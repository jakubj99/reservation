<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
<%@include file="/header.jsp" %>
<span style="color: #dc143c; ">
<c:out value="${error_message}"/>
</span>
<h2>User Form:</h2>
        <form action="/user/register" method="post">

            <div>
                <label for="username">Username:</label>
                <input id="username" name="username" type="text"/>
            </div>
            <div>
                <label for="email">Mail:</label>
                <input id="email" name="email" type="email" />
            </div>
            <div>
                <label for="password">Password:</label>
                <input id="password" name="password" type="password" />
            </div>
            <div>
                <label for="password-confirm">Password Confirmation:</label>
                <input id="password-confirm" name="password-confirm" type="password"/>
            </div>
            <div>
                <input type="submit" value="Register">
            </div>
        </form>
</body>
</html>