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


    <form:form method="POST" action="${contexPath}/deleteFlight" modelAttribute="flight" >
    <p style="color: red; text-align: center"><c:out value="${error}"/></p>
        <h2>Delete Flight</h2>
        <table>
            <tr>
                <td>Enter Flight Number:</td>
                <td><form:input type="text" path="flightNum" class="form-control"/></td>
            </tr>
            <tr>
                <td></td>
                <td><form:button type="submit">Delete</form:button></td>
            </tr>
        </table>
    </form:form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>