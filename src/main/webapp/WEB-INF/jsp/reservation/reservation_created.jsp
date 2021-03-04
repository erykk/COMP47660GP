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
<jsp:include page="../nav.jsp"/>
<div style="text-align: center;">
    <h2>Reservation Successful!</h2>
    <h2>Save your reservation reference number for looking up your reservation at a later time: <jsp:useBean id="reservation" scope="request" type="ie.ucd.COMP47660GP.entities.Reservation"/>
    <c:out value="${reservation.reservation_id}"/></h2>
</div>
<div>

</div>
</body>
</html>
