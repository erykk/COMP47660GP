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

    <table style="margin-left: auto; margin-right: auto">
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

    <%--@elvariable id="booking" type="ie.ucd.COMP47660GP.model.Booking"--%>
    <form:form method="post" modelAttribute="booking" action="/create-reservation">
        <table style="margin-left: auto; margin-right: auto">
            <tr><td><h3>Passenger Information</h3></td></tr>
            <c:forEach items="${booking.users}" var="user" varStatus="status">
                <tr><td><h4>Passenger ${status.count}</h4></td></tr>
                <tr>
                    <td>First Name:</td>
                    <td><input name="users[${status.index}].firstName" value="${user.firstName}"/></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><input name="users[${status.index}].lastName" value="${user.lastName}"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input name="users[${status.index}].email" value="${user.email}"/></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input name="users[${status.index}].address" value="${user.address}"/></td>
                </tr>
                <tr>
                    <td>Phone Number:</td>
                    <td><input name="users[${status.index}].phoneNum" value="${user.phoneNum}"/></td>
                </tr>
            </c:forEach>

            <tr><td><h3>Payment Information</h3></td></tr>
            <tr>
                <td>Card number:</td>
                <td><form:input type="tel" inputmode="numeric" pattern="[0-9\s]{13,19}" maxlength="19" path="creditCard.cardNum"/></td>
            </tr>
            <tr>
                <td>Card owner's name:</td>
                <td><form:input path="creditCard.name"/></td>
            </tr>
            <tr>
                <td>CVV:</td>
                <td><form:input maxlength="3" type="number" path="creditCard.securityCode"/></td>
            </tr>
            <tr>
                <td>Expiry Date:</td>
                <td><form:input type="month" path="dateStr"/></td>
            </tr>
            <tr>
                <td></td>
                <td><form:button type="submit">Submit</form:button></td>
            </tr>
            <tr>
                <td><jsp:useBean id="flight" scope="request" type="ie.ucd.COMP47660GP.entities.Flight"/>
                <form:input hidden="true" value="${flight.id}" path="flightID"/></td>
            </tr>

        </table>

    </form:form>
</div>
</body>
</html>
