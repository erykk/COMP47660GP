<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add Credit Card</title>

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

    <form:form method="POST" action="${contexPath}/creditCard" modelAttribute="cardCredentials" class="form-signin">
        <h2 class="form-signin-heading">Add Credit Card</h2>
        <spring:bind path="cardNum">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="cardNum" class="form-control" placeholder="Card Number"
                            autofocus="true"></form:input>
                <form:errors path="cardNum"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="expiryDate">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="expiryDate" path="expiryDate" class="form-control" placeholder="Expiry Date"></form:input>
                <form:errors path="expiryDate"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="cvv">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="cvv" class="form-control"
                            placeholder="CVV</form:input>
                <form:errors path="cvv"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Add card</button>
    </form:form>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>