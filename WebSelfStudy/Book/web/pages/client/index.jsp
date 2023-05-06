<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>首页</title>

	<%--	包含base标签以及css样式--%>
	<%@include file="/pages/common/head.jsp"%>

</head>
<body>

<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
	<span class="wel_word">便民云购药</span>
	<div>
		<c:if test="${empty sessionScope.user}">

			<a href="pages/user/login.jsp">登录</a> |
			<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
			<a href="pages/cart/cart.jsp">购物车</a>
			<a href="pages/manager/manager.jsp">后台管理</a>
		</c:if>
		<c:if test="${not empty sessionScope.user}">
			<span>欢迎<span class="um_span">${sessionScope.user.username}</span>请遵医嘱用药</span>
			<%--					<a href="pages/order/order.jsp">我的订单</a>--%>
			<a href="pages/cart/cart.jsp">购物车</a>
			<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
			<a href="pages/manager/manager.jsp">后台管理</a>
		</c:if>

	</div>
</div>
<div id="main">
	<div id="book">
		<div class="book_cond">

		</div>
		<div style="text-align: center">
			<c:choose>
				<%-- 购物车为空 --%>
				<c:when test="${ empty cart.items }">
					<span>您的购物车为空！</span>
					<div> &nbsp; </div>
				</c:when>
				<c:otherwise>
					<span>您的购物车中有${ cart.totalCount }件商品</span>
					<div>
						您刚刚将<span style="color: #ff0000">${ sessionScope.last_product }</span>加入到了购物车中
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<%--&lt;%&ndash;			--%>





		<c:forEach items="${requestScope.page.items}" var="drug">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${drug.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">药名:</span>
						<span class="sp2">${drug.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">功效:</span>
						<span class="sp2">${drug.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${drug.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${drug.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${drug.stock}</span>
					</div>
					<div class="book_add">
						<a href="cartServlet?action=addItem&id=${ drug.id }">加入购物车</a>
							<%--						<button bookid="${book.id}" class="addToCart">加入购物车</button>--%>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<%@include file="/pages/common/page_nav.jsp"%>>


</div>

<div id="bottom">
		<span>
			便民云购药.Copyright &copy;2022
		</span>
</div>
</body>
</html>