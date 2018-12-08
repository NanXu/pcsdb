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
	$("#draftEvent").click(function() {
		var url = "${ctx}/event/draft/toDraftEvent";
		window.parent.changeIFrame(url);
	});
	
	$("#eventDraftEditButton").click(function() {
		var msg="";
		$("#eventDraftList").find("input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).val();
			}
		});
		if(msg=="") {
			window.parent.topWin.Dialog.alert("请选择要编辑的数据");
		} else {
			var checkArr = msg.split(",");
			if(checkArr.length > 2) {
				window.parent.topWin.Dialog.alert("只能单条修改事故信息");
			} else {
				var id = msg.substring(1, msg.length);
				var url = "${ctx}/event/draft/editEvent/"+id;
				window.parent.changeIFrame(url);
			}
		}
	});
});

/**
 * 显示详细信息
 */
function detailDraftEvents(id) {
	var url = "${ctx}/event/draft/detailEvents/"+id;
	window.parent.changeIFrame(url);
}
</script>
</head>
<body>
	<form action="${ctx}/event/draft/listEvents" id="searchForm" method="post">
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
				
				<!-- <tr>
					<td colspan="6">
						<button type="submit"><span class="icon_find">查询</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button"><span class="icon_reload">重置</span></button>
					</td>
				</tr> -->
			</table>
		</div>	 --%>
	</form>
	
		<div class="box2" panelTitle="AIDS数据源管理列表">
			<div style="height:35px;">
				<div class="float_left padding5">
					<button type="button" id="draftEvent"><span class="icon_add">添加事故信息</span></button>&nbsp;
					<button type="button" id="eventDraftEditButton"><span class="icon_edit">事故编辑</span></button>&nbsp;
					<!-- <button type="button" id="aidsRawDataIntoDb"><span class="icon_lock">数据入库</span></button>&nbsp; -->
				</div>
			</div>
			
			<table class="tableStyle" mode="list" id="eventDraftList" useCheckbox="true" selectRowButtonOnly="false">
				<tr>
					<th width="5%" align="center"></th>
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
							<input type="checkbox" name="reportNumber" value="${event.id }" />
						</td>
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
							<a onclick="detailDraftEvents('${event.id }')">详细信息</a>
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