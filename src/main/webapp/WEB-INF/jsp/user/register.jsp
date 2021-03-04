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
    <jsp:include page="../nav.jsp"/>
    <br>
    <div class="container">

        <form:form method="post" modelAttribute="userCredentials" action="${contextPath}/secureRegister">
            <table style="margin: 0 auto">
                <tr><td colspan="2"><h3>Sign Up for our Executive member account.</h3></td></tr>
                <tr>
                    <td>First Name:</td>
                    <td><form:input class="form-control" path="firstName"/></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><form:input class="form-control" path="lastName"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><form:input class="form-control" type="email" path="email"/></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><form:input class="form-control" path="address"/></td>
                </tr>
                <tr>
                    <td>Phone Number:</td>
                    <td><form:input class="form-control" type="tel" path="phoneNum"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td>
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                            <form:errors path="password"></form:errors>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Verify Password</td>
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
                    <td><form:button class="btn btn-lg btn-outline-primary btn-block" type="submit">Submit</form:button></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>