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
	
	$("#saveMenuButton").click(function() {
		var valid = validateForm("#addMenuForm");
		//var valid = $("#addUserForm").validationEngine({returnIsValid: true});
		if(valid){
			var serialize = $("#addMenuForm").serialize();
			var url = '${ctx}/system/menu/addMenu';
			$.post(url, serialize, function(data) {
				if(data.success) {
					window.parent.topWin.Dialog.alert(data.msg+"|提示",function(){
						window.parent.changeIFrame('${ctx}/system/menu/listMenus');
						window.parent.topWin.Dialog.close();
					});
				} else {
					window.parent.topWin.Dialog.alert(data.msg+"|提示",function(){
						window.parent.topWin.Dialog.close();
					});
				}
			}, "json");
		}
	});
	
	$("#saveMenuCloseButton").click(function() {
		window.parent.changeIFrame('${ctx}/system/menu/listMenus');
		window.parent.topWin.Dialog.close();
	});
});

</script>
</head>
<body>
	<div style="width:100%;height:100%;">
		<br/>
		<form id="addMenuForm" method="post" target="frmright" showOnMouseOver="false">
			<input type="hidden" name="parentId" value="${parentId }">
		    <table class="tableStyle" formMode="line" >
				<tr>
					<td width="25%">菜单名称：</td>
					<td width="75%">
						<input type="text" style="width:70%;" name="name" value="${name }"
						class="validate[required,ajax[${ctx }/system/menu/isMenuNameExist|* 菜单名称已存在!]] float_left" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<c:if test="${parentId ne '0' }">
					<tr>
						<td width="25%">URL：</td>
						<td width="75%">
							<input type="text" style="width:70%;" name="url" class="validate[required] float_left" />
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						</td>
					</tr>
				</c:if>
				<tr>
					<td width="25%">顺序：</td>
					<td width="75%">
						<input type="text" style="width:70%;" name="order" class="validate[required] float_left" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
		    </table>
		    <center>
		    	<br/>
		    	<button type="button" id="saveMenuButton"><span>保存</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" id="saveMenuCloseButton"><span>关闭</span></button>
				<br/>
				<br/>
			</center>
  		</form>
	</div>
	<center></center>
</body>
</html>