<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Home - An Airline</title>
    <title>An Airline</title>
    <link href="${pageContext.request.contextPath}/resources/css/main.css"
          rel="stylesheet">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg header">
        <a class="navbar-brand" href="#"><img id="logo" src="images/airplane.png" alt="Image"></a>
            <div>
                <h1>British Airways</h1>
            </div>
        </nav>
    </header>
<jsp:include page="nav.jsp"/>
<div style="text-align: center;">
    <%--@elvariable id="flight" type="ie.ucd.COMP47660GP.entities.Flight"--%>
    <form:form method="get" modelAttribute="flight" action="/flight">
        <table>
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
</div>
</body>
</html>