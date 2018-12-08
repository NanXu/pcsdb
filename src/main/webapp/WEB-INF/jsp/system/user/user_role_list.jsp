<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../include/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../../include/head.jsp"%>
</head>
<body>
	<form action="${ctx}/system/user/listUserRoles" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<div class="box2" panelTitle="查询条件">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">用户名:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="username" value="${userRole.username }"/>
					</td>
					<td width="13%">角色名：</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="roleName" value="${userRole.roleName }"/>
					</td>
					<td width="13%">用户状态：</td>
					<td width="20%">
						<select  name="status" selWidth="177" selectedValue="${userRole.status}" prompt="请选择用户状态" 
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
			<table class="tableStyle" mode="list" id="userRoleList" useCheckbox="true" selectRowButtonOnly="false">
				<tr>
					<th width="10%" align="center">用户名</th>
					<th width="10%" align="center">角色名</th>
					<th width="10%" align="center">用户状态</th>
				</tr>
				<c:forEach var="userRole" items="${page.list}" varStatus="status">
					<tr>
						<td align="center">${userRole.username }</td>
						<td align="center">${userRole.roleName }</td>
						<td align="center">
							<c:if test="${userRole.status eq '1' }">
								<font color="green">已启用</font>
							</c:if>
							<c:if test="${userRole.status ne '1' }">
								<font color="red">已禁用</font>
							</c:if>
						</td>
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