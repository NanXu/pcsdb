<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../include/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加数据来源字典页面</title>
<%@ include file="../../include/head.jsp"%>

<script type="text/javascript">
$(function() {
	
	$("#saveUserButton").click(function() {
		var valid = validateForm("#addUserForm");
		//var valid = $("#addUserForm").validationEngine({returnIsValid: true});
		if(valid){
			var serialize = $("#addUserForm").serialize();
			var url = '${ctx}/system/user/addUser';
			$.post(url, serialize, function(data) {
				if(data.success) {
					window.parent.topWin.Dialog.alert("用户添加成功|提示",function(){
						window.parent.changeIFrame('${ctx}/system/user/listUsers');
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
	
	$("#editDataSourceCloseButton").click(function() {
		window.parent.changeIFrame('${ctx}/dictionary/dataSource/listDataSources');
		window.parent.topWin.Dialog.close();
	});
});

</script>
</head>
<body>
	<div style="width:100%;height:100%;">
		<br/>
		<form id="editDataSourceForm" method="post" target="frmright" showOnMouseOver="false">
			<input type="hidden" id="id" name="id" value="${dataSource.id }">
		    <table class="tableStyle" formMode="line" >
				<tr>
					<td width="25%">来源标识</td>
					<td width="75%">
						<input type="text" style="width:70%;" name="key" value="${dataSource.key }" />
					</td>
				</tr>
				<tr>
					<td width="25%">来源值</td>
					<td width="75%">
						<input type="text" style="width:70%;" name="value" value="${dataSource.value }" 
						class="validate[required,custom[noSpecialCaracters],exemptString[admin|不允许以admin为登录名],ajax[${ctx }/system/user/isLoginNameExist|* 登录名已存在!]] float_left" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
		    </table>
		    <center>
		    	<br/>
		    	<button type="button" id="editDataSourceButton"><span>保存</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" id="editDataSourceCloseButton"><span>关闭</span></button>
				<br/>
				<br/>
			</center>
  		</form>
	</div>
	<center></center>
</body>
</html>