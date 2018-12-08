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
});

</script>
</head>
<body>
	<form action="${ctx}/log/validate/listLogs" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<div class="box2" panelTitle="查询条件">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">数据标识:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="dataID" value="${validateLog.dataID }"/>
					</td>
					<td width="13%">数据状态：</td>
					<td width="20%">
						<select  name="status" selWidth="177" selectedValue="${validateLog.status}" prompt="请选择数据状态" 
						data='{"list":[{"value":"1","key":"成功"},{"value":"0","key":"失败"}]}'></select>
					</td>
					<td>
						<button type="submit"><span class="icon_find">查询</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button"><span class="icon_reload">重置</span></button>
					</td>
				</tr>
				
				<!-- <tr>
					<td colspan="6">
						<button type="submit"><span class="icon_find">查询</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button"><span class="icon_reload">重置</span></button>
					</td>
				</tr> -->
			</table>
		</div>	
	</form>
	
		<div class="box2" panelTitle="数据入库日志列表">
			<!-- <div style="height:35px;">
				<div class="float_left padding5">
					<button type="button" id="importAidsRawData"><span class="icon_import">装载数据</span></button>&nbsp;
					<button type="button" id="aidsRawDataIntoDb"><span class="icon_lock">数据入库</span></button>&nbsp;
				</div>
			</div> -->
			<table class="tableStyle" mode="list" id="userList" useCheckbox="true" selectRowButtonOnly="false">
				<tr>
					<th width="5%" align="center"></th>
					<th width="10%" align="center">数据标识</th>
					<th width="10%" align="center">入库时间</th>
					<th width="10%" align="center">入库状态</th>
					<th width="10%" align="center">操作</th>
				</tr>
				<c:forEach var="validateLog" items="${page.list}" varStatus="status">
					<tr>
						<td class="ali02">
							<input type="checkbox" name="id" value="${validateLog.id }" />
						</td>
						<td align="center">${validateLog.dataID }</td>
						<td align="center">
							<fmt:formatDate value="${validateLog.cteateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td align="center">
							<c:if test="${validateLog.status eq '1' }">
								<font color="green">成功</font>
							</c:if>
							<c:if test="${validateLog.status ne '1' }">
								<font color="red">失败</font>
							</c:if>
						</td>
						<td align="center">
							<c:if test="${validateLog.status eq '1' }">
								--
							</c:if>
							<c:if test="${validateLog.status ne '1' }">
								<a onclick="validateLogDetail('${validateLog.id}')">日志详情</a>
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