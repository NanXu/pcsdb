<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../include/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户页面</title>
<%@ include file="../../include/head.jsp"%>

<script type="text/javascript">
$(function() {
	
	$("#saveEventUserButton").click(function() {
		var valid = validateForm("#addEventUserForm");
		//var valid = $("#addUserForm").validationEngine({returnIsValid: true});
		if(valid){
			var serialize = $("#addEventUserForm").serialize();
			var url = '${ctx}/eventUser/user/addUser';
			$.post(url, serialize, function(data) {
				if(data.success) {
					window.parent.topWin.Dialog.alert("用户添加成功|提示",function(){
						window.parent.changeIFrame('${ctx}/eventUser/user/listUsers');
						window.parent.topWin.Dialog.close();
					});
				} else {
					window.parent.topWin.Dialog.alert("用户添加失败|提示",function(){
						window.parent.topWin.Dialog.close();
					});
				}
			}, "json");
		}
	});
	
	$("#saveEventUserCloseButton").click(function() {
		window.parent.changeIFrame('${ctx}/eventUser/user/listUsers');
		window.parent.topWin.Dialog.close();
	});
});

</script>
</head>
<body>
	<div style="width:100%;height:100%;">
		<br/>
		<form id="addEventUserForm" method="post" target="frmright" showOnMouseOver="false">
		    <table class="tableStyle" formMode="line" >
				<tr>
					<td width="25%">用户名</td>
					<td width="75%">
						<input type="text" style="width:70%;" name="username" value="${username }"
						class="validate[required,exemptString[admin|不允许以admin为用户名],ajax[${ctx }/system/user/isUsernameExist|* 用户名已存在!]] float_left" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<tr>
					<td width="25%">登录名</td>
					<td width="75%">
						<input type="text" style="width:70%;" name="loginName" value="${loginName }" 
						class="validate[required,custom[noSpecialCaracters],exemptString[admin|不允许以admin为登录名],ajax[${ctx }/system/user/isLoginNameExist|* 登录名已存在!]] float_left" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<tr>
					<td width="25%">密码</td>
					<td width="75%">
						<input type="password" style="width:70%;" name="password" value="${password }"
						class="validate[required,length[6,11],passwordStrength] float_left" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<tr>
					<td width="25%">性别</td>
					<td width="75%">
						<select  name="sex" selWidth="177" selectedValue="${sex}" prompt="请选择会员性别" 
						data='{"list":[{"value":"1","key":"男"},{"value":"0","key":"女"}]}' class="validate[required] float_left">
						</select>
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<tr>
					<td width="25%">电话号码</td>
					<td width="75%">
						<input type="text" style="width:70%;" name="phoneNumber" value="${phoneNumber }"
						class="validate[custom[mobilephone]] float_left" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
		    </table>
		    <center>
		    	<br/>
		    	<button type="button" id="saveEventUserButton"><span>保存</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" id="saveEventUserCloseButton"><span>关闭</span></button>
				<br/>
				<br/>
			</center>
  		</form>
	</div>
	<center></center>
</body>
</html>