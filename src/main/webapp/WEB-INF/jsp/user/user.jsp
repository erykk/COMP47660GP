<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Your Account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>
    <jsp:include page="../nav.jsp"/>
<div class="container">

    <form:form method="GET" modelAttribute="userCredentials" class="form-signin">
        <h2 class="form-signin-heading">Account Details</h2>
        <table>
            <tr>
                <td>Email:</td>
                <td>
                        <form:input type="text" path="email" class="form-control" placeholder="${email}"
                            autofocus="true"></form:input>
                </td>
            </tr>
            <tr>
                <td>First Name:</td>
                <td><form:input type="text" path="firstName" class="form-control" placeholder="${firstName}"
                    autofocus="true"></form:input></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><form:input type="text" path="lastName" class="form-control" placeholder="${lastName}"
                    autofocus="true"></form:input></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><form:input type="text" path="address" class="form-control" placeholder="${address}"
                    autofocus="true"></form:input></td>
            </tr>
            <tr>
                <td>Phone Number:</td>
                <td><form:input type="text" path="phoneNum" class="form-control" placeholder="${phoneNum}"
                    autofocus="true"></form:input></td>
            </tr>

            <tr>
                <td>Password:</td>
                <td>
                        <form:input type="password" path="password" class="form-control" placeholder="${password}"></form:input>
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

    <script>
        function updateUserDetails(address, firstName, lastName, phone, email) {
            <%--id = ${reservation.reservation_id}--%>

            // fetch(window.location.protocol + '://' + window.location.hostname + ':' + window.location.port + '/reservation/' + id, {
            fetch(window.location.protocol + 'editPersonalDetails/' + address + '/' + firstName + '/' + lastName + '/' + phone + '/' + email, {
                method: 'PUT',
                body: JSON.stringify({
                    completed: true
                }),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            })

            location.reload()
        }

    </script>
</body>
</html>