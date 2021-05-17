<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add New Flight</title>
</head>

<body>
<jsp:include page="../nav.jsp"/>
<div class="container">


    <form:form method="POST" action="${contexPath}/addFlight" modelAttribute="flight">
        <h2>Add New Flight</h2>
        <table>
            <tr>
                <td>Source:</td>
                <td><form:input type="text" path="source" class="form-control"/></td>
            </tr>
            <tr>
                <td>Destination:</td>
                <td> <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="destination" class="form-control"></form:input>
                    <form:errors path="destination"></form:errors>
                </div></td>
            </tr>
            <tr>
                <td>Flight Num:</td>
                <td><form:input type="text" path="flightNum" class="form-control"/></td>
            </tr>
<%--            <tr>--%>
<%--                <td>CVV:</td>--%>
<%--                <td> <div class="form-group ${status.error ? 'has-error' : ''}">--%>
<%--                    <form:input type="password" path="securityCode" class="form-control"></form:input>--%>
<%--                    <form:errors path="securityCode"></form:errors>--%>
<%--                </div></td>--%>
<%--            </tr>--%>

            <tr>
                <td></td>
                <td><form:button type="submit">Save Edit</form:button></td>
            </tr>
        </table>
    </form:form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>