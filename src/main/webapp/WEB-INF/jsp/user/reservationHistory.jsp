<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Reservation - An Airline</title>
    <link href="${pageContext.request.contextPath}/resources/css/main.css"
          rel="stylesheet">
</head>
<body>
<jsp:include page="../nav.jsp"/>
<br>
<div style="text-align: center;">
    <caption><h2>Reservation History</h2></caption>
            <table style="margin-left: auto; margin-right: auto; border: 1px solid black">
                <thead>
                    <tr>
                        <th style="border: 1px solid black;">Reservation ID</th>
                        <th style="border: 1px solid black;">Origin</th>
                        <th style="border: 1px solid black;">Destination</th>
                        <th style="border: 1px solid black;">Departure Time</th>
                        <th style="border: 1px solid black;">Is Cancelled?</th>
                        <th style="border: 1px solid black;">Cancel Reservation?</th>
                    </tr>
                </thead>
            <c:forEach items="${reservations}" var="reservation">
                <tbody>
                    <tr>
                        <td style="border: 1px solid black;"><c:out value="${reservation.reservation_id}"/> </td>
                        <td style="border: 1px solid black;"><c:out value="${reservation.flight.source}"/> </td>
                        <td style="border: 1px solid black;"><c:out value="${reservation.flight.destination}"/> </td>
                        <td style="border: 1px solid black;"><c:out value="${reservation.flight.dateTime}"/> </td>
                        <td style="border: 1px solid black;"><c:out value="${reservation.cancelled}"/> </td>
                        <td style="border: 1px solid black;"><a href="/user/deleteReservation/${reservation.user.username}/${reservation.reservation_id}">Cancel Reservation</a></td>
                    </tr>
                </tbody>
            </c:forEach>
            </table>
<%--    <form:form method="POST" action="/user/deleteReservation/${reservation.user.username}">--%>
<%--        <form:button class="btn btn-lg btn-primary" type="submit">Cancel</form:button>--%>
<%--    </form:form>--%>
</div>
</body>
</html>
