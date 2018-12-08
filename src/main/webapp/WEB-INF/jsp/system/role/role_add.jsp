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
	
	$("#saveRoleButton").click(function() {
		var valid = validateForm("#addRoleForm");
		//var valid = $("#addUserForm").validationEngine({returnIsValid: true});
		if(valid){
			var serialize = $("#addRoleForm").serialize();
			var url = '${ctx}/system/role/addRole';
			$.post(url, serialize, function(data) {
				if(data.success) {
					window.parent.topWin.Dialog.alert("角色添加成功|提示",function(){
						window.parent.changeIFrame('${ctx}/system/role/listRoles');
						window.parent.topWin.Dialog.close();
					});
				} else {
					window.parent.topWin.Dialog.alert("角色添加失败|提示",function(){
						window.parent.topWin.Dialog.close();
					});
				}
			}, "json");
		}
	});
	
	$("#saveRoleCloseButton").click(function() {
		window.parent.changeIFrame('${ctx}/system/role/listRoles');
		window.parent.topWin.Dialog.close();
	});
});

</script>
</head>
<body>
	<div style="width:100%;height:100%;">
		<br/>
		<form id="addRoleForm" method="post" target="frmright" showOnMouseOver="false">
		    <table class="tableStyle" formMode="line" >
				<tr>
					<td width="25%">角色名称：</td>
					<td width="75%">
						<input type="text" style="width:70%;" name="name" value="${name }"
						class="validate[required,custom[chinese],ajax[${ctx }/system/role/isRoleNameExist|* 角色名称已存在!]] float_left" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<tr>
					<td width="25%">角色描述：</td>
					<td width="75%">
						<textarea rows="3" cols="15" name="description" class="validate[required,length[0,200]] float_left"></textarea>
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
		    </table>
		    <center>
		    	<br/>
		    	<button type="button" id="saveRoleButton"><span>保存</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" id="saveRoleCloseButton"><span>关闭</span></button>
				<br/>
				<br/>
			</center>
  		</form>
	</div>
	<center></center>
</body>
</html>