<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>客舱烟雾数据库系统</title>
<link href="<%=basePath%>/css/login/skin/style.css" rel="stylesheet" type="text/css" id="skin"/>

<script type="text/javascript" src="<%=basePath%>/js/md5.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/login/jquery-1.4.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/login/login.js"></script>

<!--引入弹窗组件start-->
<script type="text/javascript" src="<%=basePath%>/js/login/zDialog/zDrag.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/login/zDialog/zDialog.js"></script>
<!--引入弹窗组件end-->

<!--修正IE6支持透明png图片start
<script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>-->
<!--修正IE6支持透明png图片end-->

<!--居中显示start-->
<script type="text/javascript" src="<%=basePath%>/js/login/center-plugin.js"></script>
<script>
	$(function(){
		 $('.login_main').center();
		 document.getElementById("loginName").focus();
		 $("#loginName").keydown(function(event){
		 	if(event.keyCode==13){
				login();
			}
		 });
		 $("#password").keydown(function(event){
		 	if(event.keyCode==13){
				login();
			}
		 });
		 
		 $("#loginButton").click(function() {
			 var errorMsg = "";
				//console.log("login");
				var loginName = $("input[name=loginName]").val();
				var password = hex_md5($("input[name=password]").val());
				var user = {
					"loginName" : loginName,
					"password" : password
				};
				//alert(password);
				if(!loginName){
					errorMsg += "&nbsp;&nbsp;用户名不能为空!";
				}
				if(!password){
					errorMsg += "&nbsp;&nbsp;密码不能为空!";
				}

				if(errorMsg != ""){
					$(".login_info").html(errorMsg);
					$(".login_info").show();
				} else {
					$(".login_info").show();
					$(".login_info").html("&nbsp;&nbsp;正在登录中...");
					$.ajax({
						type : "post",
						dataType : "json",
						data : user,
						contentType : "application/x-www-form-urlencoded;charset=UTF-8",
						url : "login",
						// 同步async: false,（默认是true）;
						// 如上：false为同步，这个方法中的Ajax请求将整个浏览器锁死，
						// 只有test.jsp执行结束后，才可以执行其它操作。
						async : false,
						success : function(data) {
							if (false == data.loginResult) {
								$(".login_info").html(data.msg);
								 return false;
							} else if (true == data.loginResult) {
								$(".login_info").html(data.msg);
								window.location="<%=basePath%>/system/index"; 
							}
						},
						error : function() {
							$(".login_info").html("服务器发生故障，请尝试重新登录！");
						}
					});
				}
			});
	});
</script>
<!--居中显示end-->
<style>
/*提示信息*/	
#cursorMessageDiv {
	position: absolute;
	z-index: 99999;
	border: solid 1px #cc9933;
	background: #ffffcc;
	padding: 2px;
	margin: 0px;
	display: none;
	line-height:150%;
}
/*提示信息*/
</style>
</head>
<body>
	<div class="login_main">
		<div class="login_top">
			<div class="login_title"></div>
		</div>
		<div class="login_middle">
			<div class="login_middleleft"></div>
			<div class="login_middlecenter">
					<form id="loginForm" class="login_form" method="post">
					<div class="login_user"><input type="text" id="loginName" name="loginName" /></div>
					<div class="login_pass"><input type="password" id="password" name="password" /></div>
					<div class="clear"></div>
					<div class="login_button">
						<div class="login_button_left"><input type="button" id="loginButton"/></div>
						<div class="login_button_right"><input type="reset" value=""/></div>
						<div class="clear"></div>
					</div>
					</form>
					<div class="login_info" style="display:none;"></div>
			</div>
			<div class="login_middleright"></div>
			<div class="clear"></div>
		</div>
		<div class="login_bottom">
			<div class="login_copyright">版权所有：中国民航大学适航中心</div>
		</div>
	</div>
</body>
</html>
