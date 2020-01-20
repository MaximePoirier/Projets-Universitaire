<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: maxou
  Date: 26/09/2019
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form:form action="home" method="post">
    <label name ="login">Login</label><input name="login" type="text"/>
    <label name="password">Password</label><input name="password" type="password"><br/>

    <button type="submit">Login</button>
</form:form>
</body>
</html>
