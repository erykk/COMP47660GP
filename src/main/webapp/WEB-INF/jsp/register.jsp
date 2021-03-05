<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create Executive Account</title>
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

    <form:form method="post" modelAttribute="userCredentials" action="${contextPath}/secureRegister">
        <table>
            <tr><td><h3>Sign Up for our Executive member account.</h3></td></tr>
            <tr>
                <td>First Name:</td>
                <td><form:input path="firstName"/></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><form:input path="lastName"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input type="email" path="email"/></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><form:input path="address"/></td>
            </tr>
            <tr>
                <td>Phone Number:</td>
                <td><form:input type="tel" path="phoneNum"/></td>
            </tr>
            <tr>
                <td>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                        <form:errors path="password"></form:errors>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="password" path="verifyPassword" class="form-control"
                                    placeholder="Verify your password"></form:input>
                        <form:errors path="verifyPassword"></form:errors>
                    </div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><form:button type="submit">Submit</form:button></td>
            </tr>
        </table>
    </form:form>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>