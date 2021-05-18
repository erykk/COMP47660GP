<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>An Airline</title>
    <link href="${pageContext.request.contextPath}/resources/css/main.css"
          rel="stylesheet">
</head>
<body>
<jsp:include page="nav.jsp"/>
<br>
<div style="text-align: center;">

    <h2>Admin Page</h2>

            <table>
                <colgroup>
                    <col span="2" style="width: 20%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 20%;">
                </colgroup>
                <tr>
                    <th style="border: 1px solid black;">Resource</th>
                    <th style="border: 1px solid black;">Actions</th>
                </tr>
                <tr>
                    <td style="border: 1px solid black;"> Flight </td>
                    <td style="border: 1px solid black;"><a href="/addFlight">Add Flight</a></td>
                </tr>
                <tr>
                    <td style="border: 1px solid black;"> Flight </td>
                    <td style="border: 1px solid black;"><a href="/findFlight">Edit Flight</a></td>
                </tr>
                <tr>
                    <td style="border: 1px solid black;"> Flight </td>
                    <td style="border: 1px solid black;"><a href="/deleteFlight">Delete Flight</a></td>
                </tr>
                <tr>
                    <td style="border: 1px solid black;"> Reservation </td>
                    <td style="border: 1px solid black;"><a href="/flight">Add Reservation</a></td>
                </tr>
                <tr>
                    <td style="border: 1px solid black;"> Reservation </td>
                    <td style="border: 1px solid black;"><a href="/admin/reservation">Edit Reservation</a></td>
                </tr>
                <tr>
                    <td style="border: 1px solid black;"> Reservation </td>
                    <td style="border: 1px solid black;"><a href="/admin/deleteReservation">Delete Reservation</a></td>
                </tr>
            </table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>