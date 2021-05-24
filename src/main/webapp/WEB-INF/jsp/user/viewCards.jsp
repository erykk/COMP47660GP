<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Your Cards</title>
</head>

<body>
    <jsp:include page="../nav.jsp"/>
    <div class="container">
        <table style="margin: 0 auto; text-align: center">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Card Number</th>
                    <th>Expiry Date</th>
                    <th></th>
                </tr>
            </thead>
            <c:forEach items="${creditCards}" var="creditcard">
                <tbody>
                    <tr>
                        <td><c:out value="${creditcard.name}"/></td>
                        <td><c:out value="${creditcard.stringRepresentation}"/></td>
                        <td><c:out value="${creditcard.expiryDate}"/></td>
                        <td><a href="/editCreditCardDetails/${creditcard.user.username}/${creditcard.id}">Edit</a></td>
                        <td><a href="/editCreditCardDetails/${creditcard.user.username}/${creditcard.id}/delete">Delete</a></td>
                    </tr>
                </tbody>
            </c:forEach>
            <tr>
                <td>
                    <h4 class="text-center btn btn-lg btn-outline-primary"><a href="${contextPath}/registerCard/${user.username}">Add Card</a></h4>
                </td>
            </tr>
        </table>
    </div>

</body>
</html>