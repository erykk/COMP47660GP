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
                    <td>Username:</td>
                    <td>
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="username" class="form-control" placeholder="Username"  onkeypress="clear()"></form:input>
                            <br>

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

                        </div>
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
                <br>
                <span  id="password_strength"></span>
                <br>
                <tr>
                    <td></td>
                    <td><form:button class="btn btn-lg btn-outline-primary btn-block" type="submit">Submit</form:button></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>