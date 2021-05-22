<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

<nav class="navbar navbar-expand-lg navbar-light bg-light" >
    <div class="container">
        <a class="navbar-brand" href="/">
            <img id="logo" src="/images/airplane.png" alt="Image" width="200px;">
        </a>
        <h2 class="">British Airways</h2>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
            &nbsp;&nbsp;&nbsp;
            <c:if test="${logged_in == false}">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
            </c:if>
            &nbsp;&nbsp;&nbsp;
            <c:if test="${logged_in == false}">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register">Register as Executive</a></li>
            </c:if>
            &nbsp;&nbsp;&nbsp;
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/flight">All Flights</a></li>
            <c:if test="${logged_in == false}">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/guestReservation">View Reservation</a></li>
            </c:if>
            <c:if test="${logged_in == true}">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/reservation">View Reservation</a></li>
            </c:if>


            <c:if test="${logged_in == true}">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user">Account</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </c:if>

            <c:if test="${logged_in == true}">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin">Admin</a></li>
            </c:if>
        </ul>
    </div>
</nav>

<%--<form:form method="GET" action="${pageContext.request.contextPath}/user/${user.username}" modelAttribute="user">--%>
<%--    <c:if test="${logged_in == true}">--%>
<%--        <form:button class="btn btn-lg btn-primary" type="submit">Account2</form:button></td>--%>
<%--        &lt;%&ndash;                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user">Account</a></li>&ndash;%&gt;--%>
<%--    </c:if>--%>
<%--</form:form>--%>
