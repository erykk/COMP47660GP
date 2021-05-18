<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Edit Credit Card</title>
</head>

<body>
<jsp:include page="../nav.jsp"/>
<div class="container">


    <form:form method="POST" action="${contexPath}/editFlight" modelAttribute="flight" >
        <h2>Edit FLight</h2>
        <table>
            <tr>
                <td>Flight Number:</td>
                <td> <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="flightNum" readonly="true" class="form-control"></form:input>
                    <form:errors path="flightNum"></form:errors>
                </div></td>
            </tr>
            <tr>
                <td>Source:</td>
                <td><form:input type="text" path="source" class="form-control"/></td>
            </tr>
            <tr>
                <td>Destination:</td>
                <td> <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="destination"  class="form-control"></form:input>
                    <form:errors path="destination"></form:errors>
                </div></td>
            </tr>
            <tr>
                <td>Current Date/Time:</td>
                <td><form:input type="text" path="dateTime"  readonly="true"  class="form-control"/></td>
            </tr>
            <tr>
                <td>New Date:</td>
                <td><form:input type="text" path="date" class="form-control"/></td>
            </tr>
            <tr>
                <td>New Time:</td>
                <td><form:input type="text" path="time" class="form-control"/></td>
            </tr>
            <tr>
                <td></td>
                <td><form:button type="submit">Save Edit</form:button></td>
            </tr>
        </table>
    </form:form>
</div>
<script>

    // onsubmit="return ajaxpost()"
function ajaxpost () {
// (A) GET FORM DATA
var data = new FormData();
data.append("source", document.getElementById("source").value);
data.append("destination", document.getElementById("destination").value);
data.append("date", document.getElementById("date").value);
data.append("time", document.getElementById("time").value);
    data.append("dateTime", document.getElementById("dateTime").value);
    data.append("flightNum", document.getElementById("flightNum").value);

// (B) AJAX REQUEST
// NOTE - AJAX WILL NOT WORK WITH file://
var xhr = new XMLHttpRequest();
xhr.open('POST', "1c-server.html");
xhr.onload = function () {
console.log(this.response);
console.log(this.status);

// (B1) SERVER NOT RESPONDING OR SOMETHING
// HTTP response 200 is OK
// See https://developer.mozilla.org/en-US/docs/Web/HTTP/Status for more
if (xhr.status !== 200) {

alert("SERVER ERROR");
alert(xhr.status);
}

// (B2) WHEN SERVER IS DONE
else {
if (xhr.response === "OK") {
alert("SUCCESSFUL!");
} else {
alert("FAILURE!");
}
}
};
xhr.send(data);

// (C) PREVENT FORM SUBMIT
return false;
}
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>