<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <%--@elvariable id="reservation" type="ie.ucd.COMP47660GP.entities.Reservation"--%>
    <%--@elvariable id="user" type="ie.ucd.COMP47660GP.entities.User"--%>
    <%--@elvariable id="flight" type="ie.ucd.COMP47660GP.entities.Flight"--%>
    <form:form method="get" modelAttribute="reservation" action="/guestReservation">
    <table style="margin-left: auto; margin-right: auto">
        <tr>
            <td>Enter reservation ID:</td>
            <td>
                <c:if test="${reservation.reservation_id == 0}">
                    <input class="form-control" type="number" id="reservation_id" name="reservation_id">
                </c:if>

                <c:if test="${reservation.reservation_id != 0}">
                    <form:input class="form-control" type="number" path="reservation_id"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input class="btn btn-lg btn-outline-primary btn-block" type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
    </form:form>

    <c:choose>
    <c:when test="${not empty user}">
    <caption><h2>Reservation Details</h2></caption>
    <table style="margin-left: auto; margin-right: auto; border: 1px solid black">
        <thead>
        <tr>
            <th style="border: 1px solid black;">Reservation ID</th>
            <th style="border: 1px solid black;">First Name</th>
            <th style="border: 1px solid black;">Last Name</th>
            <th style="border: 1px solid black;">Origin</th>
            <th style="border: 1px solid black;">Destination</th>
            <th style="border: 1px solid black;">Departure Time</th>
            <th style="border: 1px solid black;">Is Cancelled?</th>
            <th style="border: 1px solid black;">Actions</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td style="border: 1px solid black;"><c:out value="${reservation.reservation_id}"/> </td>
            <td style="border: 1px solid black;"><c:out value="${user.firstName}"/> </td>
            <td style="border: 1px solid black;"><c:out value="${user.lastName}"/> </td>
            <td style="border: 1px solid black;"><c:out value="${flight.source}"/> </td>
            <td style="border: 1px solid black;"><c:out value="${flight.destination}"/> </td>
            <td style="border: 1px solid black;"><c:out value="${flight.dateTime}"/> </td>
            <td style="border: 1px solid black;"><c:out value="${reservation.cancelled}"/> </td>
            <td style="border: 1px solid black;"><a href="/user/deleteGuestReservation/${reservation.reservation_id}">Cancel Reservation</a></td>
        </tr>
        </tbody>
    </table>
    </c:when>
    <c:when test="${not empty error}"><h3 style="color: red"><c:out value="${error}" escapeXml="false" /></h3></c:when>
    </c:choose>

    <script>
        function cancelReservation(id) {
            <%--id = ${reservation.reservation_id}--%>

            // fetch(window.location.protocol + '://' + window.location.hostname + ':' + window.location.port + '/reservation/' + id, {
            fetch(window.location.protocol + 'reservation/' + id, {
                method: 'PATCH',
                body: JSON.stringify({
                    completed: true
                }),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            })

            location.reload()
        }

    </script>

</body>
</html>
