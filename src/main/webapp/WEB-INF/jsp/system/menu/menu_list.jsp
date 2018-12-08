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
	
	/**
	 * 菜单节点添加
	 * 选中一个父级节点添加子菜单;
	 * 未选择默认添加根节点(根节点不需要设置URL值)
	 */
	$("#menuAddButton").click(function() {
		var msg="";
		$("#menuList").find("input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).val()
			}
		})
		//添加根节点
		if(msg=="") {
			window.parent.topWin.Dialog.confirm("确认要添加根节点菜单吗？", function() {
				var url = "${ctx}/system/menu/toAddMenu/"+0;
				window.parent.topWin.Dialog.open({URL:url,Title:"添加菜单",Width:640,Height:300});
			});
		} else {
			var array = msg.split(",");
			if(array.length == 2) {
				var url = "${ctx}/system/menu/toAddMenu/"+array[1];
				window.parent.topWin.Dialog.open({URL:url,Title:"添加菜单",Width:640,Height:300});
			} else {
				window.parent.topWin.Dialog.alert("只能选择一个父级菜单进行添加");
			}
		}
	});
	 
	 /**
	  * 菜单节点删除
	  * 选中一个父级节点添加子菜单;
	  * 未选择默认添加根节点(根节点不需要设置URL值)
	  */
	$("#menuDeleteButton").click(function() {
		var msg="";
		$("#menuList").find("input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).val()
			}
		})
		if(msg=="") {
			window.parent.topWin.Dialog.alert("请选择要删除的数据");
		} else {
			window.parent.topWin.Dialog.confirm("确认要删除菜单信息吗？ ", function() {
				$.ajax({
					  type: 'POST',
					  url: '${ctx}/system/menu/validateMenuHasAssociationRole',
					  data: {'deleteIDs': msg},
					  dataType: 'JSON',
					  success: function(data){
							if(data.success){
								$.ajax({
									  type: 'POST',
									  url: '${ctx}/system/menu/hasRootMenu',
									  data: {'deleteIDs': msg},
									  dataType: 'JSON',
									  success: function(data){
											if(data.success){
												window.parent.topWin.Dialog.confirm(data.msg, function() {
													$.ajax({
														  type: 'POST',
														  url: '${ctx}/system/menu/deleteMenu',
														  data: {'deleteIDs': msg},
														  dataType: 'JSON',
														  success: function(data){
																if(data.success){
																	 window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
																		 window.parent.changeIFrame('${ctx}/system/menu/listMenus');
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
											} else{
												//删除叶子节点
												$.ajax({
													  type: 'POST',
													  url: '${ctx}/system/menu/deleteMenu',
													  data: {'deleteIDs': msg},
													  dataType: 'JSON',
													  success: function(data){
															if(data.success){
																 window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
																	 window.parent.changeIFrame('${ctx}/system/menu/listMenus');
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
									  },
									  error:function(){
									  }			  
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
});
</script>
</head>
<body>
	<form action="${ctx}/system/menu/listMenus" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
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
	
		<div class="box2" panelTitle="菜单信息列表">
			<div style="height:35px;">
				<div class="float_left padding5">
					<button type="button" id="menuAddButton"><span class="icon_add">添加菜单</span></button>&nbsp;
					<button type="button" id="menuDeleteButton"><span class="icon_delete">删除菜单</span></button>&nbsp;
				</div>
				
			</div>
			<table class="tableStyle" mode="list" id="menuList" useCheckbox="true" selectRowButtonOnly="false">
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
</body>
</html>