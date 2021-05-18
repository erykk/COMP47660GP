<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
</head>

<body>
<jsp:include page="../nav.jsp"/>
<div class="container">


    <form:form method="GET" action="${contexPath}/findFlight" modelAttribute="flight">
        <h2></h2>
        <h2></h2>
        <h2>There is no flight that matches that flight number</h2>
        <h2></h2>
        <h2>Please try again</h2>
        <table>
            <tr>
                <td>
                    <h4 class="text-center btn btn-lg btn-outline-primary"><a href="${contextPath}/findFlight">Find Flight</a></h4>
                </td>
                <td>
                    <h4 class="text-center btn btn-lg btn-outline-primary"><a href="${contextPath}/admin">Admin Page</a></h4>
                </td>
            </tr>
        </table>
    </form:form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>