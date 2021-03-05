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
    <h2>Save your reservation reference number(s) for looking up your reservation at a later time: </h2>
    <table style="margin-left: auto; margin-right: auto">
        <jsp:useBean id="reservations" scope="request" type="java.util.List"/>
        <c:forEach items="${reservations}" var="reservation" varStatus="status">
            <tr>
                <td><c:out value="${reservation.reservation_id}"/></td>
            </tr>
        </c:forEach>
    </table>



</div>
<div>

</div>
</body>
</html>
