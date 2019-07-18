<%--
  Created by IntelliJ IDEA.
  User: dong
  Date: 2019/7/15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>index</title>
</head>
<body>
<h1>welcome</h1>

<form action="${pageContext.request.contextPath}/teacher">
    name: <input type="text" name="name"> <br>
    <input type="submit" value="submit">
</form>
<a href="/teacher">teacher</a>
</body>
</html>
