<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../include/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑用户页面</title>
<%@ include file="../../include/head.jsp"%>

<script type="text/javascript">
$(function() {
	
	$("#editButton").click(function() {
		var valid = validateForm("#editEventUserForm");
		//var valid = $("#addUserForm").validationEngine({returnIsValid: true});
		if(valid){
			var serialize = $("#editEventUserForm").serialize();
			var url = '${ctx}/eventUser/user/editUser';
			$.post(url, serialize, function(data) {
				if(data.success) {
					window.parent.topWin.Dialog.alert("用户修改成功|提示",function(){
						window.parent.changeIFrame('${ctx}/eventUser/user/listUsers');
						window.parent.topWin.Dialog.close();
					});
				} else {
					window.parent.topWin.Dialog.alert("用户修改失败|提示",function(){
						window.parent.topWin.Dialog.close();
					});
				}
			}, "json");
		}
	});
	
	$("#editCloseButton").click(function() {
		window.parent.changeIFrame('${ctx}/eventUser/user/listUsers');
		window.parent.topWin.Dialog.close();
	});
});

</script>
</head>
<body>
	<div style="width:100%;height:100%;">
		<br/>
		<form id="editEventUserForm" method="post" target="frmright" showOnMouseOver="false">
		<input type="hidden" name="id" value="${user.id }" />
		    <table class="tableStyle" formMode="line" >
				<tr>
					<td width="25%">用户名</td>
					<td width="75%">
						<input type="text" style="width:70%;" name="username" value="${user.username }"
						class="validate[required,exemptString[admin|不允许以admin为用户名]]] float_left" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<tr>
					<td width="25%">登录名</td>
					<td width="75%">
						<input type="text" style="width:70%;" name="loginName" value="${user.loginName }" 
						class="validate[required,custom[noSpecialCaracters],exemptString[admin|不允许以admin为登录名]]] float_left" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<tr>
					<td width="25%">性别</td>
					<td width="75%">
						<select  name="sex" selWidth="177" selectedValue="${user.sex}" prompt="请选择会员性别" 
						data='{"list":[{"value":"1","key":"男"},{"value":"0","key":"女"}]}' class="validate[required] float_left">
						</select>
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<tr>
					<td width="25%">电话号码</td>
					<td width="75%">
						<input type="text" style="width:70%;" name="phoneNumber" value="${user.phoneNumber }"
						class="validate[custom[mobilephone]] float_left" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
		    </table>
		    <center>
		    	<br/>
		    	<button type="button" id="editButton"><span>保存</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" id="editCloseButton"><span>关闭</span></button>
				<br/>
				<br/>
			</center>
  		</form>
	</div>
	<center></center>
</body>
</html>