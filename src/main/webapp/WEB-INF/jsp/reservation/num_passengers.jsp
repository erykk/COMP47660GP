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

    <table>
        <caption><h2>Flights</h2></caption>
        <tr>
            <th style="border: 1px solid black;">Origin</th>
            <th style="border: 1px solid black;">Destination</th>
            <th style="border: 1px solid black;">Date</th>
            <th style="border: 1px solid black;">Flight Number</th>
        </tr>
        <tr>
            <td style="border: 1px solid black;"><c:out value="${flight.source}"/> </td>
            <td style="border: 1px solid black;"><c:out value="${flight.destination}"/> </td>
            <td style="border: 1px solid black;"><c:out value="${flight.dateTime}"/> </td>
            <td style="border: 1px solid black;"><c:out value="${flight.flightNum}"/> </td>
        </tr>
    </table>
    <%--@elvariable id="flight" type="ie.ucd.COMP47660GP.entities.Flight"--%>
    <form:form method="get" modelAttribute="flight" action="/create-reservation/${flight.id}">
        <table style="margin-left: auto; margin-right: auto">
            <tr>
                <td>Number of passengers: </td>
                <td><input class="form-control" type="number" min="1" max="30" id="num_passengers" name="num_passengers"></td>
            </tr>
            <tr>
                <td></td>
                <td><form:button type="submit">Submit</form:button></td>
            </tr>
        </table>
    </form:form>

</div>
</body>
</html>
