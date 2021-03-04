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
    <header>
        <nav class="navbar navbar-expand-lg header">
        <a class="navbar-brand" href="#"><img id="logo" src="images/airplane.png" alt="Image"></a>
            <div>
                <h1>British Airways</h1>
            </div>
        </nav>
    </header>
<div class="container">
    <h2>
        <a href="${pageContext.request.contextPath}/">Home</a>
        &nbsp;&nbsp;&nbsp;
    </h2>
    <form:form method="GET" modelAttribute="cardCredentials" class="form-signin">
        <h2 class="form-signin-heading">Card Details</h2>
        <table>
            <tr>
                <td>Name on Card:</td>
                <td>
                        <form:input type="text" path="name" class="form-control" placeholder="${name}"
                            autofocus="true"></form:input>
                </td>
            </tr>
            <tr>
                <td>Card Number:</td>
                <td>
                        <form:input type="text" path="cardNum" class="form-control" placeholder="${cardNum}"
                            autofocus="true"></form:input>
                </td>
            </tr>
            <tr>
                <td>Expiry Date:</td>
                <td><form:input type="text" path="expiryDate" class="form-control" placeholder="${expiryDate}"
                    autofocus="true"></form:input></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                        <form:input type="password" path="securityCode" class="form-control" placeholder="${securityCode}"></form:input>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Save Changes"/>
                </td>
            </tr>
            <tr>
                <td>
                    <h4 class="text-center"><a href="${contextPath}/registerCard">Add Card</a></h4>
                </td>
            </tr>
        </table>
    </form:form>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>