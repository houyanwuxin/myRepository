<%--
  Created by IntelliJ IDEA.
  User: 厚颜无心
  Date: 2022/11/7
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${usenameCookie.username.value}<br>
<form action="http://localhost:8080/cookie/loginServlet" method="get">
    用户名<input type="text" name="username" value="${cookie.username.value}"><br>
    密码<input type="password" name="password"><br>
    <input type="submit" value="登录">

</form>
</body>
</html>
