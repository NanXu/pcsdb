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
	//事故审批功能
	$("#eventApproveButton").click(function() {
		var msg="";
		$("#eventList").find("input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).val();
			}
		})
		if(msg==""){
			window.parent.topWin.Dialog.alert("请选择要审批的数据");
		} else {
			var array = msg.split(",");
			if(array.length == 2) {
				var url = "${ctx}/event/approve/toApproveEvent/"+ array[1];
				window.parent.changeIFrame(url);
				//window.parent.topWin.Dialog.open({URL:url,Title:"角色菜单关联",Width:340,Height:400});
			} else {
				window.parent.topWin.Dialog.alert("只能选择一条数据进行审批");
			}
		}
	});
});
/**
 * 显示详细信息
 */
function detailApproveEvents(id) {
	var url = "${ctx}/event/approve/detailEvents/"+id;
	window.parent.changeIFrame(url);
}
</script>
</head>
<body>
	<form action="${ctx}/event/approve/listEvents" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<div class="box2" panelTitle="查询条件">
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
		</div>	
	</form>
	
		<div class="box2" panelTitle="源管理列表">
			<div style="height:35px;">
				<div class="float_left padding5">
					<button type="button" id="eventApproveButton"><span class="icon_pencil">审批</span></button>
				</div>
			</div>
			<table class="tableStyle" mode="list" id="eventList" useCheckbox="true" selectRowButtonOnly="false">
				<tr>
					<th width="5%" align="center"></th>
					<th width="5%" align="center">事故编号</th>
					<th width="5%" align="center">数据来源</th>
					<th width="10%" align="center">源数据编号</th>
					<th width="5%" align="center">事故类型</th>
					<th width="8%" align="center">当地日期</th>
					<th width="10%" align="center">最后起飞地</th>
					<th width="10%" align="center">目的地</th>
					<th width="10%" align="center">事件发生地</th>
					<th width="10%" align="center">天气状况</th>
					<th width="10%" align="center">飞机损害程度</th>
					<th width="10%" align="center">飞行阶段</th>
					<th width="10%" align="center">操作</th>
				</tr>
				<c:forEach var="event" items="${page.list}" varStatus="status">
					<tr>
						<td class="ali02">
							<input type="checkbox" name="id" value="${event.id }" />
						</td>
						<td align="center">${event.id }</td>
						<td align="center">${event.source }</td>
						<td align="center">${event.sourceId }</td>
						<td align="center">${event.eventType }</td>
						<td align="center">
							<fmt:formatDate value="${event.localDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td align="center">${event.lastDeparturePoint }</td>
						<td align="center">${event.destinationLocal }</td>
						<td align="center">${event.eventLocation }</td>
						<td align="center">${event.weatherConditions }</td>
						<td align="center">${event.aircraftDamage }</td>
						<td align="center">${event.phaseFlight }</td> 
						<td align="center">
							<a onclick="detailApproveEvents('${event.id }')">详细信息</a>
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