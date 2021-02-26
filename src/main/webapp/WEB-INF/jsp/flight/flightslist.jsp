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
<%--    <form method="get" action="${pageContext.request.contextPath}/flight" >--%>
<%--        <table style="margin-left: auto; margin-right: auto; border: 1px solid black">--%>
<%--            <tr>--%>
<%--                <td><label for="origin">Origin:</label></td>--%>
<%--                <td><input id="origin" name="origin"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><label for="dest">Destination:</label></td>--%>
<%--                <td><input id="dest" name="dest"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><label for="date">Date:</label></td>--%>
<%--                <td><input type="date" id="date" name="date"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td><input type="submit" value="Submit"/></td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </form>--%>

    <%--@elvariable id="flight" type="ie.ucd.COMP47660GP.entities.Flight"--%>
    <form:form method="get" modelAttribute="flight" action="/flight">
        <table style="margin-left: auto; margin-right: auto">
            <tr>
                <td>Origin:</td>
                <td>
                    <form:select path="source">
                        <form:option value="Select" label="--Select--" />
                        <form:options items="${origins}" />
                    </form:select>
                </td>
            </tr>

            <tr>
                <td>Destination:</td>
                <td>
                    <form:select path="destination">
                        <form:option value="Select" label="--Select--" />
                        <form:options items="${destinations}" />
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>
                    <form:input path="dateTime" type="date"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Submit"/>
                </td>
            </tr>
        </table>
    </form:form>

    <c:choose>
        <c:when test="${empty flights}"><h2 style="color:red">No results...</h2></c:when>
        <c:when test="${not empty flights}">
            <table style="margin-left: auto; margin-right: auto; border: 1px solid black">
                <caption><h2>Flights</h2></caption>
                <colgroup>
                    <col span="2" style="width: 20%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 20%;">
                </colgroup>
                <tr>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Date</th>
                    <th>Flight Number</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${flights}" var="flight">
                    <tr>
                        <td style="text-align: center; vertical-align: middle;"><c:out value="${flight.source}"/> </td>
                        <td style="text-align: center; vertical-align: middle;"><c:out value="${flight.destination}"/> </td>
                        <td style="text-align: center; vertical-align: middle;"><c:out value="${flight.dateTime}"/> </td>
                        <td style="text-align: center; vertical-align: middle;"><c:out value="${flight.flightNum}"/> </td>
                        <td style="text-align: center; vertical-align: middle;"><a href="/reservation/${flight.id}">Make Reservation</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
</div>
</body>
</html>