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
    <jsp:include page="../nav.jsp"/>
<div class="container">


    <form:form method="POST" action="${contexPath}/creditCard" modelAttribute="cardCredentials">
    <h2 style="text-align: center">Add Credit Card</h2>
    <table style="margin: 0 auto">
        <tr>
            <td>Name on Card:</td>
            <td><form:input type="text" path="name" class="form-control" placeholder="Name"/></td>
        </tr>
        <tr>
            <td>Card Number:</td>
            <td> <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="cardNum" class="form-control" placeholder="Card Number"></form:input>
                <form:errors path="cardNum"></form:errors>
            </div></td>
        </tr>
        <tr>
            <td>Expiry Date:</td>
            <td><form:input type="text" path="expiryDate" class="form-control" placeholder="MM/YY"/></td>
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
            <td><form:button class="btn btn-lg btn-primary" type="submit">Add Card</form:button></td>
        </tr>
    </table>
    </form:form>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>