<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<%--	包含base标签以及css样式--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color: #ff0000;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<%--	包含静态菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
		</div>

		<div id="main">
			<form action="manager/drugServlet" method="get">
				<input type="hidden" name="pageNo" value="${param.pageNo}" />
				<input type="hidden" name="action" value="${empty param.id?"add":"update"}" />
				<input type="hidden" name="id" value="${requestScope.drug.id}" />
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>功效主治</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>
					<tr>
						<td><input name="name" type="text" value="${requestScope.drug.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.drug.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.drug.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.drug.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.drug.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>
				</table>
			</form>
		</div>

		<%--		静态包含页脚--%>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>