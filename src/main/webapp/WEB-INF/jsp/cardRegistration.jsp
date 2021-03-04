<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add Credit Card</title>
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

    <form:form method="POST" action="${contexPath}/creditCard" modelAttribute="cardCredentials">
        <table>
    <h2>Add Credit Card</h2>
        <tr>
            <td>Card Number:</td>
            <td> <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="cardNum" class="form-control" placeholder="Card Number"></form:input>
                <form:errors path="cardNum"></form:errors>
            </div></td>
        </tr>
        <tr>
            <td>Expiry Date:</td>
            <td><form:input path="expiryDate"/></td>
        </tr>
        <tr>
            <td>CVV:</td>
            <td> <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="securityCode" class="form-control" placeholder="CVV"></form:input>
                <form:errors path="securityCode"></form:errors>
            </div></td>
        </tr>

        <tr>
            <td></td>
            <td><form:button type="submit">Add Card</form:button></td>
        </tr>
    </table>
    </form:form>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>