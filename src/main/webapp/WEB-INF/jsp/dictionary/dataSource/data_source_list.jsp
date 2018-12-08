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

function edit(id) {
	var url = "${ctx}/dictionary/dataSource/toEditDataSource/"+id;
	window.parent.topWin.Dialog.open({URL:url,Title:"编辑数据来源字典",Width:640,Height:340});
}
</script>
</head>
<body>
	<form action="${ctx}/dictionary/dataSource/listDataSources" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<div class="box2" panelTitle="查询条件">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">来源标识:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="key" value="${dataSource.key }"/>
					</td>
					<td width="13%">来源值：</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="value" value="${dataSource.value }"/>
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
	
		<div class="box2" panelTitle="数据来源字典列表">
			<!-- <div style="height:35px;">
				<div class="float_left padding5">
					<button type="button" id="dataSourceAddButton"><span class="icon_add">添加数据来源</span></button>&nbsp;
				</div>
				
			</div> -->
			<table class="tableStyle" mode="list" id="userList" useCheckbox="true" selectRowButtonOnly="false">
				<tr>
					<!-- <th width="5%" align="center">序号</th> -->
					<th width="5%" align="center"></th>
					<th width="10%" align="center">来源标识</th>
					<th width="10%" align="center">来源值</th>
					<th width="10%" align="center">操作</th>
				</tr>
				<c:forEach var="dataSource" items="${page.list}" varStatus="status">
					<tr>
						<td class="ali02">
							<input type="checkbox" name="id" value="${dataSource.id }" />
						</td>
						<%-- <td align="center">${status.count+(pageNo-1)*(pageSize)}</td> --%>
						<td align="center">${dataSource.key }</td>
						<td align="center">${dataSource.value }</td>
						<td align="center">
							<a onclick="edit('${dataSource.id}')">编辑</a>&nbsp;
							<%-- <a onclick="delete('${dataSource.id}')">删除</a> --%>
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