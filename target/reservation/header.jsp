<%--<table style="width: 100%">--%>
<%--<tr>--%>
<%--<td><a href="/">Home</a></td>--%>
<%--<td><a href="/user/register">Register</a></td>--%>
<%--<td><a href="/login">Login</a></td>--%>
<%--<c:if test="${not empty sessionScope.logged_username}">--%>
<%--<td><a href="/event/add">Add Event:</a></td>--%>
<%--<td><a href="/event/list">Event List:</a></td>--%>
<%--<td><a href="/logout">Logout</a></td>--%>
<%--</c:if>--%>
<%--</tr>--%>
<%--</table>--%>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Register App</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/">Home</a></li>
                <li><a href="/login">Login</a></li>
                <li><a href="/user/register">Register</a></li>
                <c:if test="${not empty sessionScope.logged_username}">
                    <li><a href="/event/add">Add Event</a></li>
                    <li><a href="/event/list">Event List</a></li>
                    <li><a href="/logout">Logout</a></li>
                </c:if>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<c:if test="${not empty logged_username}">
    Hello <c:out value="${sessionScope.logged_username}"/>
</c:if>