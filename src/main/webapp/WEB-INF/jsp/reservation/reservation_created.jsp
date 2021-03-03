<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>An Airline</title>
    <link href="${pageContext.request.contextPath}/resources/css/main.css"
          rel="stylesheet">
</head>
<body>
<div style="text-align: center;">
    <h1>An Airline - Cheap Flights</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/">Home</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/login">Login</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/register">Register</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/reservation">View Reservation</a>
    </h2>
</div>
<div style="text-align: center;">
    <h2>Reservation Successful!</h2>
    <h2>Save your reservation reference number for looking up your reservation at a later time: <jsp:useBean id="reservation" scope="request" type="ie.ucd.COMP47660GP.entities.Reservation"/>
    <c:out value="${reservation.reservation_id}"/></h2>
</div>
<div>

</div>
</body>
</html>
