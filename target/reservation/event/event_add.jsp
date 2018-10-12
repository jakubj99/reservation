<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event Add</title>
</head>
<body>
<%@include file="/header.jsp" %>
<span style="color: #dc143c; ">
<c:out value="${error_message}"/>
</span>
<h2>Event Form:</h2>
<form action="/event/add" method="post">
<input name="owner_id" type="hidden" value="<c:out value="${sessionScope.logged_id}"/>">
    <div>
        <label for="name">Name:</label>
        <input id="name" name="name" type="text"/>
    </div>
    <div>
        <label for="description">Description:</label>
        <input id="description" name="description" type="text" />
    </div>
    <div>
        <label for="time">Time:</label>
        <input id="time" name="time" type="datetime-local" />
    </div>
    <div>
        <label for="length">Length (in minutes):</label>
        <input id="length" name="length" type="number"/>
    </div>
    <div>
        <input type="submit" value="Add">
    </div>
</form>
</body>
</html>
