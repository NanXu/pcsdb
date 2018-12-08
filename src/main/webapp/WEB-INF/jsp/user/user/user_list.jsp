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
	$("#userAddButton").click(function() {
		var url = "${ctx}/eventUser/user/toAddUser";
		//window.parent.changeIFrame(url);
		window.parent.topWin.Dialog.open({URL:url,Title:"添加用户",Width:640,Height:340});
	});
	
	//编辑用户
	$("#userEditButton").click(function() {
		var msg="";
		$("#userList").find("input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).val()
			}
		});
		if(msg=="") {
			window.parent.topWin.Dialog.alert("请选择要编辑的数据");
		} else {
			var checkArr = msg.split(",");
			if(checkArr.length > 2) {
				window.parent.topWin.Dialog.alert("只能单条修改用户信息");
			} else {
				var id = msg.substring(1, msg.length);
				var url = "${ctx}/eventUser/user/toEditUser/"+id;
				window.parent.topWin.Dialog.open({URL:url,Title:"编辑用户",Width:640,Height:240});
			}
		}
	});
	
	//删除用户
	$("#userDeleteButton").click(function() {
		var msg="";
		$("#userList").find("input[type=checkbox]").each(function(){
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
					  url: '${ctx}/eventUser/user/deleteUser',
					  data: {'deleteIDs': msg},
					  dataType: 'JSON',
					  success: function(data){
							if(data.success){
								 window.parent.topWin.Dialog.alert("删除成功|提示",function(){
									 window.parent.changeIFrame('${ctx}/eventUser/user/listUsers');
								 });
							}else{
								 window.parent.topWin.Dialog.alert("删除失败|提示",function(){
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



/**
 * 启用/禁用 操作
 */
function operate(id, status) {
	var msg = "";
	if(status == '1') {
		msg = "确定要启用该用户吗?";
	} else {
		msg = "确定要禁用该用户吗?";
	}
	window.parent.topWin.Dialog.confirm(msg,function(){
		var url = "${ctx}/eventUser/user/forbid/"+id+"/"+status;
		$.post(url, function(data) {
			if(data.success) {
				window.parent.topWin.Dialog.alert("用户状态修改成功|提示",function(){
					window.parent.changeIFrame('${ctx}/eventUser/user/listUsers');
				});
			} else {
				window.parent.topWin.Dialog.alert("用户状态修改失败|提示",function(){
				});
			}
		}, "json");
	})
}
</script>
</head>
<body>
	<form action="${ctx}/eventUser/user/listUsers" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<div class="box2" panelTitle="查询条件">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">用户名:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="username" value="${user.username }"/>
					</td>
					<td width="13%">登录名：</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="loginName" value="${user.loginName }"/>
					</td>
					<td width="13%">用户状态：</td>
					<td width="20%">
						<select  name="status" selWidth="177" selectedValue="${user.status}" prompt="请选择用户状态" 
						data='{"list":[{"value":"1","key":"启用"},{"value":"0","key":"禁用"}]}'></select>
					</td>
				</tr>
				
				<tr>
					<td colspan="6">
						<button type="submit"><span class="icon_find">查询</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button"><span class="icon_reload">重置</span></button>
					</td>
				</tr>
			</table>
		</div>	
	</form>
	<div class="box2" panelTitle="用户信息列表">
		<div style="height:35px;">
			<div class="float_left padding5">
				<button type="button" id="userAddButton"><span class="icon_add">添加用户</span></button>&nbsp;
				<button type="button" id="userEditButton"><span class="icon_add">编辑用户</span></button>&nbsp;
				<button type="button" id="userDeleteButton"><span class="icon_delete">删除用户</span></button>&nbsp;
			</div>
		</div>
		<table class="tableStyle" mode="list" id="userList" useCheckbox="true" selectRowButtonOnly="false">
			<tr>
				<!-- <th width="5%" align="center">序号</th> -->
				<th width="5%" align="center"></th>
				<th width="10%" align="center">用户名</th>
				<th width="10%" align="center">登录名</th>
				<th width="10%" align="center">性别</th>
				<th width="10%" align="center">电话号码</th>
				<th width="10%" align="center">创建时间</th>
				<th width="10%" align="center">是否管理员</th>
				<th width="10%" align="center">用户状态</th>
				<th width="10%" align="center">操作</th>
			</tr>
			<c:forEach var="user" items="${page.list}" varStatus="status">
				<tr>
					<td class="ali02">
						<input type="checkbox" name="id" value="${user.id }" />
					</td>
					<%-- <td align="center">${status.count+(pageNo-1)*(pageSize)}</td> --%>
					<td align="center">${user.username }</td>
					<td align="center">${user.loginName }</td>
					<td align="center">
						<c:if test="${user.sex eq '0' }">
							女
						</c:if>
						<c:if test="${user.sex eq '1' }">
							男
						</c:if>
					</td>
					<td align="center">${user.phoneNumber }</td>
					<td align="center">
						<fmt:formatDate value="${user.createDate }" pattern="yyyy-MM-dd"/>
					</td>
					<td align="center">
						<c:if test="${user.isAdmin eq 'Y' }">
							系统管理员
						</c:if>
						<c:if test="${user.isAdmin ne 'Y' }">
							非系统管理员
						</c:if>
					</td>
					<td align="center">
						<c:if test="${user.status eq '1' }">
							<font color="green">已启用</font>
						</c:if>
						<c:if test="${user.status ne '1' }">
							<font color="red">已禁用</font>
						</c:if>
					</td>
					<c:if test="${user.loginName eq 'admin' }">
						<td align="center">--</td>
					</c:if>
					<c:if test="${user.loginName ne 'admin' }">
						<td align="center">
							<c:if test="${user.status eq '1' }">
								<a onclick="operate('${user.id}','0')">禁用</a>
							</c:if>
							<c:if test="${user.status ne '1' }">
								<a onclick="operate('${user.id}','1')">启用</a>
							</c:if>
						</td>
					</c:if>
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