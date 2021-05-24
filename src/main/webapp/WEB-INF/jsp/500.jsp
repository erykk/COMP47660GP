<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>500 Error - Internal Server Error</title>
</head>

<body>
    <jsp:include page="nav.jsp"/>
    <h3 style="text-align: center">500 - An internal server error has occurred.</h3>
    <p style="text-align: center"><c:out value="${err}"/></p>
    <p style="text-align: center"><c:out value="${error}"/></p>
</body>
</html>