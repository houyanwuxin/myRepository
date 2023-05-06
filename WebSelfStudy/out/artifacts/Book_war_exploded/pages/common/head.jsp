<%--
  Created by IntelliJ IDEA.
  User: 厚颜无心
  Date: 2022/12/13
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% String address=request.getScheme()+
        "://"+request.getServerName()+":"+
        request.getServerPort()+
        request.getContextPath()+"/";%>

<base href="<%=address%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="jquery/script/jquery-1.7.2.js">
</script>
</body>
</html>
