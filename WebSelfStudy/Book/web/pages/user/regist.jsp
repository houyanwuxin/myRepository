<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--	包含base标签以及css样式--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}

</style>

<!--	一定要记得导入jquery库-->
<script type="text/javascript">
	/***
	 * 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
	 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
	 验证确认密码：和密码相同
	 邮箱验证：xxxxx@xxx.com
	 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
	 */
	$(function (){
		//验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
		$("#sub_btn").click(function (){
			var usernameText = $("#username").val();
			var usernamePatt = /^\w{5,12}$/;
			if(!usernamePatt.test(usernameText)){
				$("span.errorMsg").text("用户名错误");
				return false;
			}

			//验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
			var passwordText = $("#password").val();
			var passwordPatt = /^\w{5,12}$/;
			if(!passwordPatt.test(passwordText)){
				$("span.errorMsg").text("密码错误");
				return false;
			}
			// 验证确认密码：和密码相同
			var repwdText = $("#repwd").val();
			if(passwordText!=repwdText){
				$("span.errorMsg").text("确认密码和密码不一致");
				return false;
			}
			//邮箱验证：xxxxx@xxx.com
			var emailText = $("#email").val();
			var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
			if(!emailPatt.test(emailText)){
				$("span.errorMsg").text("邮箱错误");
				return false;
			}
			// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
			var codeText = $("#code").val();
			//把验证码是空格的情况排除
			if(codeText==null||$.trim(codeText)==""){
				$("span.errorMsg").text("验证码错误");
				return false;
			}
		})

		// 给验证码的图片，绑定单击事件
		$("#code_img").click(function () {
// 在事件响应的 function 函数中有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象
// src 属性表示验证码 img 标签的 图片路径。它可读，可写
// alert(this.src);
			this.src = "${basePath}kaptchaServlet.jpg?d=" + new Date();
		});

	})
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册账号</h1>
								<span class="errorMsg">
<%--									<%=request.getAttribute("mgr")==null?"":request.getAttribute("mgr")%>--%>
									${requestScope.mgr}
								</span>
							</div>
							<div class="form">
<!--								action="regist_success.jsp"-->
<!--								-->
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
<%--									value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/>--%>
									value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email"
<%--										   value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"/>--%>
										   value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" id="code" name="code"/>
<%--									<img src="" alt="" style="width: 90px; height: 39px;">--%>

									<img id="code_img"alt="" src="kaptchaServlet.jpg" style="float: right; margin-right: 40px;width: 90px; height: 39px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--		静态包含页脚--%>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>