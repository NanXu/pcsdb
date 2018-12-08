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
	$("#saveRoleMenuAssociationButton").click(function() {
		var roleId = $("#roleId").val();
		var msg="";
		$("#roleMenuAssociationList").find("input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).val()
			}
		})
		if(msg==""){
			window.parent.topWin.Dialog.alert("请选择要关联的菜单");
		} else {
			//关联多个菜单
			$.ajax({
				  type: 'POST',
				  url: '${ctx}/system/role/associationMenu',
				  data: {'roleId': roleId, 'menuIds' : msg},
				  dataType: 'JSON',
				  success: function(data){
						if(data.success){
							 window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
								 window.parent.changeIFrame('${ctx}/system/user/listUsers');
								 window.parent.topWin.Dialog.close();
							 });
						}else{
							 window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
							  });
						}
				  },
				  error:function(){
				  }			  
			});
		}
	});
	
	
	$("#saveRoleMenuAssociationCloseButton").click(function() {
		window.parent.changeIFrame('${ctx}/system/role/listRoles');
		window.parent.topWin.Dialog.close();
	});
});
</script>
</head>
<body>
	<form action="${ctx}/system/role/listMenus" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<input type="hidden" value="${roleId }" name="roleId" id="roleId">
		<div class="box2" panelTitle="查询条件">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">菜单名称:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="name" value="${menu.name }"/>
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
		<table class="tableStyle" mode="list" id="roleMenuAssociationList" useCheckbox="true" selectRowButtonOnly="false">
			<tr>
				<!-- <th width="5%" align="center">序号</th> -->
				<th width="5%" align="center"></th>
				<th width="10%" align="center">菜单名称</th>
				<th width="10%" align="center">父菜单</th>
				<th width="10%" align="center">URL</th>
				<th width="10%" align="center">顺序</th>
			</tr>
			<c:forEach var="menu" items="${page.list}" varStatus="status">
				<c:if test="${menu.parentId eq '0' }">
					<tr>
						<td class="ali02">
							<input type="checkbox" name="id" value="${menu.id }" />
						</td>
						<%-- <td align="center">${status.count+(pageNo-1)*(pageSize)}</td> --%>
						<td align="center">${menu.name }</td>
						<td align="center">根节点菜单</td>
						<td align="center">${menu.url }</td>
						<td align="center">${menu.order }</td>
					</tr>
					<c:forEach var="childMenu" items="${page.list}" varStatus="status">
						<c:if test="${childMenu.parentId eq menu.id }">
							<tr>
								<td class="ali02">
									<input type="checkbox" name="id" value="${childMenu.id }" />
								</td>
								<%-- <td align="center">${status.count+(pageNo-1)*(pageSize)}</td> --%>
								<td align="center">${childMenu.name }</td>
								<td align="center">${menu.name }</td>
								<td align="center">${childMenu.url }</td>
								<td align="center">${childMenu.order }</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>
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
	<div>
		<center>
	    	<br/>
	    	<button type="button" id="saveRoleMenuAssociationButton"><span>保存</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" id="saveRoleMenuAssociationCloseButton"><span>关闭</span></button>
			<br/>
			<br/>
		</center>
	</div>
</body>
</html>