<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Logout - An Airline</title>
</head>
<body>
<jsp:include page="../nav.jsp"/>
<br>
<div class="container">
    <h2>You have been logged out successfully.</h2>
    <a class="btn btn-lg btn-outline-secondary" href="${pageContext.request.contextPath}/">Back to main</a>
</div>
</body>
</html>
