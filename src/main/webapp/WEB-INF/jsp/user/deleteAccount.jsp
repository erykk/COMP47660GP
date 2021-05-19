<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Delete account</title>
</head>

<body>
    <jsp:include page="../nav.jsp"/>
    <br>
<%--    <div class="container" style="text-align: center">--%>
<%--        <form method="POST" action="${contextPath}/deleteAccount" class="form-signin">--%>
<%--            <h2 class="form-heading">Delete Executive Account</h2>--%>

<%--            <div class="form-group ${error != null ? 'has-error' : ''}">--%>
<%--                <span>${message}</span>--%>
<%--                <input name="username" type="text" class="form-control" placeholder="Username"--%>
<%--                       autofocus="true"/>--%>
<%--                <input name="password" type="password" class="form-control" placeholder="Password"/>--%>
<%--                <span>${error}</span>--%>
<%--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

<%--                <button class="btn btn-lg btn-outline-primary btn-block" type="submit">Delete</button>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>

    <div class="container">
        <form:form method="POST" modelAttribute="user" class="form-signin" action="/deleteAccount/${user.username}">
            <h2 class="form-signin-heading" style="text-align: center">Delete Account </h2>
            <br>
            <table style="margin: 0 auto; width: 80%">
                <tr>
<%--                    <td>Username:</td>--%>
                    <td>
                        <form:input type="text" path="username" class="form-control" placeholder="${user.username}"
                                    autofocus="true"></form:input>
                    </td>
                </tr>
                <tr>
<%--                    <td>Password:</td>--%>
                    <td><form:input type="text" path="password" class="form-control"
                                    autofocus="true" placeholder="Password"></form:input></td>
                    <td><form:input type="hidden" path="" class="form-control"
                                    autofocus="true" name="${_csrf.parameterName}" value="${_csrf.token}"></form:input></td>
                </tr>
                <tr><td></td></tr>
                <tr><td></td></tr>
                <tr><td></td></tr>
                <tr><td></td></tr>
                <tr><td></td></tr>
                <tr><td></td></tr>
                <tr>
                     <td><form:button class="btn btn-lg btn-primary" style="text-align:center" type="submit">Delete</form:button></td>
                </tr>

            </table>
        </form:form>

    </div>

</body>
</html>