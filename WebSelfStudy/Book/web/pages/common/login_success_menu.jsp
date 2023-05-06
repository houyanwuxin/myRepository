<%--
  Created by IntelliJ IDEA.
  User: 厚颜无心
  Date: 2022/12/13
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>选购</span>
<%--    <a href="pages/order/order.jsp">我的订单</a>--%>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>

</body>
</html>
