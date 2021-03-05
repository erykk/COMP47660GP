<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Your Cards</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>
    <jsp:include page="../nav.jsp"/>
<div class="container">
    <table style="margin-left: auto; margin-right: auto">
        <thead>
            <tr>
                <th>Name</th>
                <th>Card Number</th>
                <th>Expiry Date</th>
                <th></th>
            </tr>
        </thead>
        <c:forEach items="${creditCards}" var="creditcard">
            <tbody>
                <tr>
                    <td><c:out value="${creditcard.name}"/></td>
                    <td><c:out value="${creditcard.cardNum}"/></td>
                    <td><c:out value="${creditcard.expiryDate}"/></td>
                    <td><a href="/editCreditCardDetails/${creditcard.id}">Edit</a></td>
                </tr>
            </tbody>
        </c:forEach>
        <tr>
            <td>
                <h4 class="text-center"><a href="${contextPath}/registerCard">Add Card</a></h4>
            </td>
        </tr>
    </table>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>