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
	$("#saveUserRoleAssignButton").click(function() {
		var userId = $("#userId").val();
		var msg="";
		$("#userRoleAssignList").find("input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).val()
			}
		})
		if(msg==""){
			window.parent.topWin.Dialog.alert("请选择要分配的角色");
		} else {
			var array = msg.split(",");
			if(array.length == 2) {
				$.ajax({
					  type: 'POST',
					  url: '${ctx}/system/user/assignRole',
					  data: {'userId': userId, 'roleId' : array[1]},
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
			} else {
				window.parent.topWin.Dialog.alert("只能选择一个角色进行分配");
			}
		}
	});
	
	
	$("#saveUserRoleAssignCloseButton").click(function() {
		window.parent.changeIFrame('${ctx}/system/user/listUsers');
		window.parent.topWin.Dialog.close();
	});
});
</script>
</head>
<body>
	<form action="${ctx}/system/user/listRoles" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<input type="hidden" value="${userId }" name="userId" id="userId">
		<div class="box2" panelTitle="查询条件">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">角色名:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="name" value="${role.name }"/>
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
		<table class="tableStyle" mode="list" id="userRoleAssignList" useCheckbox="true" selectRowButtonOnly="false">
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
	<div>
		<center>
	    	<br/>
	    	<button type="button" id="saveUserRoleAssignButton"><span>保存</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" id="saveUserRoleAssignCloseButton"><span>关闭</span></button>
			<br/>
			<br/>
		</center>
	</div>
</body>
</html>