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
        <c:forEach items="${creditCards}" var="creditcard">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Card Number</th>
                    <th>Security Code</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><c:out value="${creditcard.name}"/></td>
                    <td><c:out value="${creditcard.cardNum}"/></td>
                    <td><c:out value="${creditcard.securityCode}"/>
                </tr>
                <tr>
                    <td>
                        <h4 class="text-center"><a href="${contextPath}/registerCard">Add Card</a></h4>
                    </td>
                </tr>
            </tbody>
        </c:forEach>
    </table>


<%--    <form:form method="GET" modelAttribute="cardCredentials"  action="/creditCard/{cardNum}">--%>
<%--        <h2 class="form-signin-heading">Card Details</h2>--%>
<%--        <table>--%>
<%--            <tr class="form-group">--%>
<%--                <td>All Cards:</td>--%>
<%--                <td>--%>
<%--                    <form:select class="form-control" path="cardNum">--%>
<%--                        <form:option value="Select" label="--Select--" />--%>
<%--                        <form:options items="${creditCards}" />--%>
<%--                    </form:select>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <input class="form-control" type="submit" value="View Card Details"/>--%>
<%--                </td>--%>
<%--            </tr>--%>

<%--            <tr>--%>
<%--                <td>--%>
<%--                    <h4 class="text-center"><a href="${contextPath}/registerCard">Add Card</a></h4>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </form:form>--%>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>