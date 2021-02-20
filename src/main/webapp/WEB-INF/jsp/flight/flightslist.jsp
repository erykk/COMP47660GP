<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>An Airline</title>
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

    </h2>
</div>
<div style="text-align: center;">
    <form method="get" action="${pageContext.request.contextPath}/flight" >
        <table style="margin-left: auto; margin-right: auto; border: 1px solid black">
            <tr>
                <td><label for="origin">Origin:</label></td>
                <td><input id="origin" name="origin"/></td>
            </tr>
            <tr>
                <td><label for="dest">Destination:</label></td>
                <td><input id="dest" name="dest"/></td>
            </tr>
            <tr>
                <td><label for="date">Date:</label></td>
                <td><input type="date" id="date" name="date"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form>
    <c:choose>
        <c:when test="${empty flights}"><h2 style="color:red">No results...</h2></c:when>
        <c:when test="${not empty flights}">
            <table style="margin-left: auto; margin-right: auto; border: 1px solid black">
                <caption><h2>Flights</h2></caption>
                <tr>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Date</th>
                    <th>Flight Number</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${flights}" var="flight">
                    <tr>
                        <td><c:out value="${flight.source}"/> </td>
                        <td><c:out value="${flight.destination}"/> </td>
                        <td><c:out value="${flight.dateTime}"/> </td>
                        <td><c:out value="${flight.flightNum}"/> </td>
                        <td><a href="/reservation/${flight.id}">Make Reservation</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
</div>
</body>
</html>