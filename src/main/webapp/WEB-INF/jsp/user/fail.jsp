<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Fail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>
    <div class="container">
        <h2><c:out value="${msg}"/></h2>
        <c:choose>
            <c:when test="${not empty flightID}">
                <a class="btn btn-lg btn-outline-secondary" href="${pageContext.request.contextPath}/create-reservation/${flightID}">Back to reservation</a>
            </c:when>
            <c:when test="${empty flightID}">
                    <a class="btn btn-lg btn-outline-secondary" href="${pageContext.request.contextPath}/">Back to main</a>
            </c:when>
        </c:choose>
    </div>
</body>
</html>