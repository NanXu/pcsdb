<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../include/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/head.jsp"%>
<script type="text/javascript">
</script>
</head>
<body>
	<form action="${ctx}/statistics/list" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<div class="box2" panelTitle="统计条件">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">起始日期:</td>
					<td width="20%">
						<input type="text" class="date" style="width:177px;" name="startDate" value="${eventStatView.startDate }"/>
					</td>
					<td width="13%">终止日期:</td>
					<td width="20%">
						<input type="text" class="date" style="width:177px;" name="endDate" value="${eventStatView.endDate }"/>
					</td>
				</tr>
				<tr>
					<td width="13%">统计类型:</td>
					<td width="20%">
						<select  name="type" selWidth="177" selectedValue="${eventStatView.type}" prompt="请选择统计类型" 
						data='{"list":[{"value":"eventType","key":"事故类型"},{"value":"aircraftDamage","key":"飞机损害程度"},
						{"value":"phaseFlight","key":"飞行阶段"},{"value":"aircraftModel","key":"飞机型号"},{"value":"aircraftMake","key":"飞机制造商"},
						{"value":"engineManufactuer","key":"发动机制造商"},{"value":"engineModel","key":"发动机型号"},{"value":"operator","key":"运营商"},
						{"value":"maintainType","key":"维修类型"},{"value":"source","key":"污染物来源"},{"value":"relateToSystem","key":"涉及系统"},
						{"value":"bugLocation","key":"故障位置"},{"value":"bugModel","key":"故障模式"},{"value":"prevent","key":"预防/紧急措施"}]}' 
						class="validate[required] float_left"></select>
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
					<td width="13%">统计条件：</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="name" value="${eventStatView.name }"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="6">
						<button type="submit"><span class="icon_find">统计</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button"><span class="icon_reload">重置</span></button>
					</td>
				</tr>
			</table>
		</div>	
	</form>
	<div class="box2" panelTitle="统计信息列表">
		<table class="tableStyle" mode="list" id="userList" useCheckbox="true" selectRowButtonOnly="false">
			<tr>
				<th width="5%" align="center">序号</th>
				<th width="10%" align="center">统计类型</th>
				<th width="10%" align="center">名称</th>
				<th width="10%" align="center">数量</th>
			</tr>
			<c:forEach var="statistics" items="${page.list}" varStatus="status">
				<tr>
					<td align="center">${status.count+(pageNo-1)*(pageSize)}</td>
					<td align="center">
						<c:if test="${statistics.type eq 'eventType' }">事故类型</c:if>
						<c:if test="${statistics.type eq 'aircraftDamage' }">飞机损害程度</c:if>
						<c:if test="${statistics.type eq 'phaseFlight' }">飞行阶段</c:if>
						<c:if test="${statistics.type eq 'aircraftModel' }">飞机型号</c:if>
						<c:if test="${statistics.type eq 'aircraftMake' }">飞机制造商</c:if>
						<c:if test="${statistics.type eq 'engineManufactuer' }">发动机制造商</c:if>
						<c:if test="${statistics.type eq 'engineModel' }">发动机型号</c:if>
						<c:if test="${statistics.type eq 'operator' }">运营商</c:if>
						<c:if test="${statistics.type eq 'maintainType' }">维修类型</c:if>
						<c:if test="${statistics.type eq 'source' }">污染物来源</c:if>
						<c:if test="${statistics.type eq 'relateToSystem' }">涉及系统</c:if>
						<c:if test="${statistics.type eq 'bugLocation' }">故障位置</c:if>
						<c:if test="${statistics.type eq 'bugModel' }">故障模式</c:if>
						<c:if test="${statistics.type eq 'prevent' }">预防/紧急措施</c:if>
						<%-- <c:if test="${statistics.type eq 'maintain' }">维修措施</c:if> --%>
					</td>
					<td align="center">${statistics.name }</td>
					<td align="center">${statistics.total }</td>
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