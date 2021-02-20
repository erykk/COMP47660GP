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
    <h3>Welcome to our landing page, you'll be landing at your destination of choice in no time..... 🤦</h3>
</div>
<div style="text-align: center;">
    <form method="get" action="${pageContext.request.contextPath}/flight" >
        <table style="margin-left: auto; margin-right: auto">
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

</div>
</body>
</html>