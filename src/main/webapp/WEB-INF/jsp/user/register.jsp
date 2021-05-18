<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create Executive Account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/password_validation.js"></script>
</head>

<body>
    <jsp:include page="../nav.jsp"/>
    <br>
    <div class="container">

        <script>
            function myFunction() {
                var x = document.getElementById("txtPassword");
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
                clear()
            }
        </script>
        <script>

            function clear() {
                document.getElementById("perrors").innerHTML = '';

            }


        </script>


        <form:form method="post" modelAttribute="userCredentials" action="${contextPath}/secureRegister">
            <table style="margin: 0 auto">
                <tr><td colspan="2"><h3>Sign Up for our Executive member account.</h3></td></tr>
                <tr>
                    <td>First Name:</td>
                    <td>
                        <form:input class="form-control" path="firstName"/>
                        <form:errors path="firstName"></form:errors>
                    </td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td>
                        <form:input class="form-control" path="lastName"/>
                        <form:errors path="lastName"></form:errors>
                    </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>
                        <form:input class="form-control" type="email" path="email"/>
                        <form:errors path="email"></form:errors>
                    </td>
                </tr>
                <tr>
                    <td>Address:</td>
                     <td>
                        <form:input class="form-control" path="address"/>
                        <form:errors path="address"></form:errors>
                    </td>
                </tr>
                <tr>
                    <td>Phone Number:</td>
                    <td>
                    <form:input class="form-control" type="tel" path="phoneNum"/>
                        <form:errors path="phoneNum"></form:errors>
                    </td>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td>
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="username" class="form-control" placeholder="Username"  onkeypress="clear()"></form:input>
                            <br>
                            <form:errors path="username"></form:errors>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td>
                        <div class="form-group ${status.error ? 'has-error' : ''}">

                            <form:input type="password" path="password" class="form-control" placeholder="Password" id="txtPassword" onkeyup="CheckPasswordStrength(this.value)"></form:input>

                            <input type="checkbox" onclick="myFunction()">Show Password
                            <br>
                            <form:errors path="password"></form:errors>
                        </div>
                    </td>
                </tr>

                <tr>
                    <td>Password Strength:</td>
                    <td>
                        <br>
                        <span  id="password_strength"></span>
                        <br>
                    </td>
                </tr>

                <tr>
                    <td>Verify Password</td>
                    <td>
                        <div class="form-group ${status.error ? 'has-error' : ''}">

                            <form:input type="password" path="passwordConfirm" class="form-control" placeholder="Confirm your password" onkeydown="clear()"></form:input>
                            <br>

                            <form:errors path="passwordConfirm" id="perrors"></form:errors>

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
</body>
</html>