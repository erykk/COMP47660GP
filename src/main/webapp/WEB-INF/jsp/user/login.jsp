<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg header">
        <a class="navbar-brand" href="#"><img id="logo" src="images/airplane.png" alt="Image"></a>
            <div>
                <h1>British Airways</h1>
                <p>Welcome to British Airways</p>
            </div>
        </nav>
    </header>
<div style="text-align: center;">
    <h1>Login</h1>
    <h2>
        <a href="${pageContext.request.contextPath}/">Home</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/register">Register</a>
        &nbsp;&nbsp;&nbsp;
    </h2>
</div>
<div style="text-align: center;">
    <form method="get" action="${pageContext.request.contextPath}/flight" >
        <table style="margin-left: auto; margin-right: auto; border: 1px solid black">
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="text" id="email" name="email"/></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password" id="password" name="password"/></td>
            </tr>

            <tr>
                <td><input type="submit" value="Login"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>