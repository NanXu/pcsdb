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
/**
 * 显示详细信息
 */
function detailApprove(remark) {
	var url = "${ctx}/event/approve/approveDetail/"+remark;
	window.parent.topWin.Dialog.open({URL:url,Title:"审批详情",Width:640,Height:340});
}
</script>
</head>
<body>
<form action="${ctx}/event/approve/listApproves" id="searchForm" method="post">
	<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
	<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
	<%-- <div class="box2" panelTitle="查询条件">
		<table class="tableStyle" formMode="transparent">
			<tr>
				<td width="13%">数据来源：</td>
				<td width="20%">
					<select  name="source" selWidth="177" selectedValue="${eventInfo.source}" prompt="请选择数据来源" 
					data='{"list":[{"value":"AIDS","key":"AIDS"},{"value":"SDR","key":"SDR"},{"value":"NTSB","key":"NTSB"}]}'></select>
				</td>
				<td width="13%">来源标识:</td>
				<td width="20%">
					<input type="text" style="width:177px;" name="sourceId" value="${eventInfo.sourceId }"/>
				</td>
				<td>
					<button type="submit"><span class="icon_find">查询</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button"><span class="icon_reload">重置</span></button>
				</td>
			</tr>
		</table>
	</div> --%>	
</form>
<div class="box2" panelTitle="源管理列表">
	<!-- <div style="height:35px;">
		<div class="float_left padding5">
			<button type="button" id="eventApproveButton"><span class="icon_pencil">审批</span></button>
		</div>
	</div> -->
	<table class="tableStyle" mode="list" id="approveList" useCheckbox="true" selectRowButtonOnly="false">
		<tr>
			<th width="5%" align="center">事故编号</th>
			<th width="10%" align="center">审批人</th>
			<th width="10%" align="center">审批时间</th>
			<th width="10%" align="center">审批结果</th>
			<th width="10%" align="center">操作</th>
		</tr>
		<c:forEach var="approve" items="${page.list}" varStatus="status">
			<tr>
				<td align="center">${approve.eventId }</td>
				<td align="center">${approve.approver }</td>
				<td align="center">
					<fmt:formatDate value="${approve.approveDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td align="center">
					<c:if test="${approve.pass eq '1' }">
						<font color="green">审批通过</font>
					</c:if>
					<c:if test="${approve.pass eq '0' }">
						<font color="red">审批不通过</font>
					</c:if>
				</td>
				<td align="center">
					<a onclick="detailApprove('${approve.remark }')">审批信息详情</a>
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