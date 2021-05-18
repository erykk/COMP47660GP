<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Home - An Airline</title>
    <title>An Airline</title>

</head>
<body>
    <jsp:include page="nav.jsp"/>
    <br>
    <div class="container">
        <%--@elvariable id="flightDetails" type="ie.ucd.COMP47660GP.model.FlightDetails"--%>
        <form:form method="get" modelAttribute="flightDetails" action="/flight2">
            <table style="margin: 0 auto; width: 80%">
                <tr class="form-group">
                    <td>Origin:</td>
                    <td>
                        <form:select class="form-control" path="source">
                            <form:option value="Select" label="--Select--" />
                            <form:options items="${origins}" />
                        </form:select>
                        <form:errors path="source"></form:errors>
                    </td>
                </tr>

                <tr>
                    <td>Destination:</td>
                    <td>
                        <form:select class="form-control" path="destination">
                            <form:option value="Select" label="--Select--" />
                            <form:options items="${destinations}" />
                        </form:select>
                        <form:errors path="destination"></form:errors>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Departure time/date</p>
                    </td>
                    <td>
                        <form:input class="form-control" path="dateTime" type="date"/>
                        <form:errors path="dateTime"></form:errors>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input class="form-control" type="submit" value="Submit"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>