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
<br>
<div style="text-align: center;">

    <%--@elvariable id="flight" type="ie.ucd.COMP47660GP.entities.Flight"--%>
    <form:form method="get" modelAttribute="flight" action="/flight">
        <table>
            <tr>
                <td>Origin:</td>
                <td>
                    <form:select class="form-control" path="source">
                        <form:option value="Select" label="--Select--" />
                        <form:options items="${origins}" />
                    </form:select>
                </td>
            </tr>

            <tr>
                <td>Destination:</td>
                <td>
                    <form:select class="form-control" path="destination">
                        <form:option value="Select" label="--Select--" />
                        <form:options items="${destinations}" />
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    Deparature time/date:
                </td>
                <td>
                    <form:input class="form-control" path="dateTime" type="date"/>
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
        <c:when test="${empty flights}"><h2 style="color:red">No results...</h2></c:when>
        <c:when test="${not empty flights}">
            <table>
                <caption><h2>Flights</h2></caption>
                <colgroup>
                    <col span="2" style="width: 20%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 20%;">
                </colgroup>
                <tr>
                    <th style="border: 1px solid black;">Origin</th>
                    <th style="border: 1px solid black;">Destination</th>
                    <th style="border: 1px solid black;">Date</th>
                    <th style="border: 1px solid black;">Flight Number</th>
                    <th style="border: 1px solid black;">Actions</th>
                </tr>
                <c:forEach items="${flights}" var="flight">
                    <tr>
                        <td style="border: 1px solid black;"><c:out value="${flight.source}"/> </td>
                        <td style="border: 1px solid black;"><c:out value="${flight.destination}"/> </td>
                        <td style="border: 1px solid black;"><c:out value="${flight.dateTime}"/> </td>
                        <td style="border: 1px solid black;"><c:out value="${flight.flightNum}"/> </td>
                        <td style="border: 1px solid black;"><a href="/create-reservation/${flight.id}">Make Reservation</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
</div>
</body>
</html>