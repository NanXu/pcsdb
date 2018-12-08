<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../include/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../../include/head.jsp"%>
<script type="text/javascript">

$(function() {
	//添加用户
	$("#roleAddButton").click(function() {
		var url = "${ctx}/system/role/toAddRole";
		//window.parent.changeIFrame(url);
		window.parent.topWin.Dialog.open({URL:url,Title:"添加角色",Width:640,Height:240});
	});
	
	//删除用户
	$("#roleDeleteButton").click(function() {
		var msg="";
		$("#roleList").find("input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).val()
			}
		})
		if(msg==""){
			window.parent.topWin.Dialog.alert("请选择要删除的数据");
		} else {
			window.parent.topWin.Dialog.confirm("确认要删除用户信息吗？ ", function() {
				//alert(msg);
				$.ajax({
					  type: 'POST',
					  url: '${ctx}/system/role/deleteRole',
					  data: {'deleteIDs': msg},
					  dataType: 'JSON',
					  success: function(data){
							if(data.success){
								 window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
									 window.parent.changeIFrame('${ctx}/system/role/listRoles');
								 });
							}else{
								 window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
								  });
							}
					  },
					  error:function(){
					  }			  
				});
			});
		}
	});
	
	$("#roleMenuButton").click(function() {
		var msg="";
		$("#roleList").find("input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).val()
			}
		})
		if(msg==""){
			window.parent.topWin.Dialog.alert("请选择要关联菜单的角色");
		} else {
			var array = msg.split(",");
			if(array.length == 2) {
				var url = "${ctx}/system/role/toRoleMenuAssociation/"+ array[1];
				window.parent.topWin.Dialog.open({URL:url,Title:"角色菜单关联",Width:340,Height:400});
			} else {
				window.parent.topWin.Dialog.alert("只能选择一个角色进行关联");
			}
		}
	});
});
</script>
</head>
<body>
	<form action="${ctx}/system/role/listRoles" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<div class="box2" panelTitle="查询条件">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">角色名:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="name" value="${role.name }"/>
					</td>
					<td width="13%">角色描述：</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="description" value="${role.description }"/>
					</td>
					<td>
						<button type="submit"><span class="icon_find">查询</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button"><span class="icon_reload">重置</span></button>
					</td>
				</tr>
			</table>
		</div>	
	</form>
	
		<div class="box2" panelTitle="角色信息列表">
			<div style="height:35px;">
				<div class="float_left padding5">
					<button type="button" id="roleAddButton"><span class="icon_add">添加角色</span></button>&nbsp;
					<button type="button" id="roleDeleteButton"><span class="icon_delete">删除角色</span></button>&nbsp;
					<button type="button" id="roleMenuButton"><span class="icon_user_group">角色分配菜单</span></button>&nbsp;
				</div>
				
			</div>
			<table class="tableStyle" mode="list" id="roleList" useCheckbox="true" selectRowButtonOnly="false">
				<tr>
					<!-- <th width="5%" align="center">序号</th> -->
					<th width="5%" align="center"></th>
					<th width="10%" align="center">角色名</th>
					<th width="10%" align="center">角色描述</th>
					<th width="10%" align="center">创建时间</th>
				</tr>
				<c:forEach var="role" items="${page.list}" varStatus="status">
					<tr>
						<td class="ali02">
							<input type="checkbox" name="id" value="${role.id }" />
						</td>
						<%-- <td align="center">${status.count+(pageNo-1)*(pageSize)}</td> --%>
						<td align="center">${role.name }</td>
						<td align="center">${role.description }</td>
						<td align="center">${role.createDate }</td>
					</tr>
				</c:forEach>
			</table>
			<div style="height: 35px;">
				<div class="float_left padding5">
					共有 <span style="color: blue;">${page.count}</span> 条记录
				</div>
				<div class="float_right padding5">
					<div class="pageNumber" total="${page.count}"
						page="${pageNo-1}" pageSize="${pageSize}"
						showSelect="true" showInput="true" id="pageNumberTools"></div>
				</div>
				<div class="clear"></div>
			</div>
		</div>	
</body>
</html>