<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jakubjankowski
  Date: 09/10/2018
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event List</title>
</head>
<body>
<%@include file="/header.jsp" %>
<h2>Your Event List:</h2>
<table style="width: 100%; border: 1px solid #000000;" >
    <thead>
    <th>Id</th>
    <th>Name</th>
    <th>Description</th>
    <th>Time</th>
    <th>Length</th>
    <th>Remove</th>
    <th>Modify</th>
    </thead>
<c:forEach var="event" items="${eventList}">
    <tr>
        <td><c:out value="${event.id}"/></td>
        <td><c:out value="${event.name}"/></td>
        <td><c:out value="${event.description}"/></td>
        <td><c:out value="${event.time}"/></td>
        <td><c:out value="${event.lenght}"/></td>
        <td>Remove</td>
        <td>Modify</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
