<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Your Account</title>
</head>

<body>
    <jsp:include page="../nav.jsp"/>
    <br>
    <div class="container">
        <form:form method="POST" modelAttribute="user" class="form-signin" action="/editPersonalDetails/${user.username}">
            <h2 class="form-signin-heading" style="text-align: center">Account Details</h2>
            <br>
            <p style="color: red; text-align: center"><c:out value="${msg}"/></p>
            <table style="margin: 0 auto; width: 80%">
                <tr>
                    <td>Email:</td>
                    <td>
                        <form:input type="text" path="email" class="form-control" placeholder="${email}"
                            autofocus="true"></form:input>
                        <form:errors path="email"></form:errors>
                    </td>
                </tr>
                <tr>
                    <td>First Name:</td>
                    <td><form:input type="text" path="firstName" class="form-control" placeholder="${firstName}"
                        autofocus="true"></form:input>
                        <form:errors path="firstName"></form:errors>
                    </td>

                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><form:input type="text" path="lastName" class="form-control" placeholder="${lastName}"
                        autofocus="true"></form:input>
                        <form:errors path="lastName"></form:errors>
                    </td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><form:input type="text" path="address" class="form-control" placeholder="${address}"
                        autofocus="true"></form:input>
                        <form:errors path="address"></form:errors>
                    </td>
                </tr>
                <tr>
                    <td>Phone Number:</td>
                    <td><form:input type="text" path="phoneNum" class="form-control" placeholder="${phoneNum}"
                        autofocus="true"></form:input>
                        <form:errors path="phoneNum"></form:errors>
                    </td>
                </tr>

                <tr>
                    <td>Password:</td>
                    <td>
                        <form:input type="password" path="password" class="form-control" placeholder=""></form:input>
                        <form:errors path="password"></form:errors>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input class="btn btn-lg btn-outline-primary" type="submit" value="Save Changes"/>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td colspan="2">
                        <h4 class="text-center form-control"><a href="${contextPath}/viewCards/${user.username}">View Saved Cards</a></h4>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <h4 class="text-center form-control"><a href="${contextPath}/registerCard/${user.username}">Add Card</a></h4>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <h4 class="text-center form-control"><a href="${contextPath}/reservationHistory/${user.username}">View Your Reservation History</a></h4>
                    </td>
<%--                    <td><a href="/user/${user.username}">Edit</a></td>--%>
                </tr>
                <tr>
                    <td colspan="2">
                        <h4 class="text-center form-control"><a style="color: red" href="${pageContext.request.contextPath}/deleteAccount">Delete Executive Account</a></h4>
                    </td>
                </tr>
            </table>
        </form:form>

    </div>
</body>
</html>