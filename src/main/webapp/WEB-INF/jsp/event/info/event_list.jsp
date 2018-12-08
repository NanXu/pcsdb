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
	//回显 数据来源的显示
	var val = "${eventView.source}";
	$("#selectTree").setValue(val);
	
	$("#exportEvent").click(function() {
		document.searchForm.action='${ctx}/event/info/exportEventData';
		searchForm.submit();
		//window.location ='${ctx}/event/info/exportEventData?sourceId='+sourceId;
	});
	
	$("#searchEvent").click(function() {
		document.searchForm.action='${ctx}/event/info/listEvents';
		searchForm.submit();
	});
	
	$("#eventEditButton").click(function() {
		var msg="";
		$("#eventList").find("input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).val()
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
				var url = "${ctx}/event/info/editEvent/"+id;
				window.parent.changeIFrame(url);
			}
		}
	});
	
	
	//删除用户
	$("#eventDeleteButton").click(function() {
		var msg="";
		$("#eventList").find("input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).val()
			}
		})
		if(msg==""){
			window.parent.topWin.Dialog.alert("请选择要删除的数据");
		} else {
			window.parent.topWin.Dialog.confirm("确认要删除事故信息吗？ ", function() {
				//alert(msg);
				$.ajax({
					  type: 'POST',
					  url: '${ctx}/event/info/deleteEvent',
					  data: {'deleteIDs': msg},
					  dataType: 'JSON',
					  success: function(data){
							if(data.success){
								 window.parent.topWin.Dialog.alert("删除成功|提示",function(){
									 window.parent.changeIFrame('${ctx}/event/info/listEvents');
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
 * 显示详细信息
 */
function detailEvents(id) {
	var url = "${ctx}/event/info/detailEvents/"+id;
	window.parent.changeIFrame(url);
}
</script>
</head>
<body>
	<form action="" id="searchForm" name="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<div class="box2" panelTitle="查询条件">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">数据来源：</td>
					<td width="20%">
						<div id="selectTree" class="selectTree" name="source" multiMode="true" noGroup="true" data='{"treeNodes":[{ "id":"AIDS", "parentId":"0", "name":"AIDS"},{ "id":"SDR", "parentId":"0", "name":"SDR"},{ "id":"NTSB", "parentId":"0", "name":"NTSB"}]}'>
						</div>
						<%-- <select  name="source" selWidth="177" selectedValue="${eventInfo.source}" prompt="请选择数据来源" 
						data='{"list":[{"value":"AIDS","key":"AIDS"},{"value":"SDR","key":"SDR"},{"value":"NTSB","key":"NTSB"}]}'></select> --%>
					</td>
					<td width="13%">源数据编号:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="sourceId" value="${eventView.sourceId }"/>
					</td>
					<td width="13%">事故类型：</td>
					<td width="20%">
						<select  name="eventType" selWidth="177" selectedValue="${eventView.eventType}" prompt="请选择事故类型" 
						data='{"list":[{"value":"INCIDENT","key":"INCIDENT"},{"value":"ACCIDENT","key":"ACCIDENT"}]}'></select>
					</td>
				</tr>
				<tr>
					<td width="13%">当地日期起:</td>
					<td width="20%">
						<input type="text" class="date" style="width:177px;" name="localDateStart" value="${eventView.localDateStart }"/>
					</td>
					<td width="13%">当地日期止:</td>
					<td width="20%">
						<input type="text" class="date" style="width:177px;" name="localDateEnd" value="${eventView.localDateEnd }"/>
					</td>
					<td width="13%">飞行阶段：</td>
					<td width="20%">
						<select  name="phaseFlight" selWidth="177" selectedValue="${eventView.phaseFlight}" prompt="请选择飞行阶段" 
						data='{"list":[{"value":"taxi","key":"taxi"},{"value":"takeoff","key":"Take off"},{"value":"climb","key":"climb"},{"value":"cruise","key":"cruise"},{"value":"descent","key":"descent"},{"value":"Approach","key":"Approach"},{"value":"landing","key":"landing"},{"value":"aborted","key":"aborted"},{"value":"other","key":"other"}]}'></select>
					</td>
				</tr>
				<tr>
					<td width="13%">故障模式:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="bugModel" value="${eventView.bugModel }"/>
					</td>
					<td width="13%">事件描述:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="eventRemarks" value="${eventView.eventRemarks }"/>
					</td>
					<td width="13%">原因描述：</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="reasonRemarks" value="${eventView.reasonRemarks }"/>
					</td>
				</tr>
				<tr>
					<td width="13%">预防/紧急措施:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="prevent" value="${eventView.prevent }"/>
					</td>
					<td width="13%">其他机组控制措施:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="controls" value="${eventView.controls }"/>
					</td>
					<td width="13%">维修措施：</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="maintain" value="${eventView.maintain }"/>
					</td>
				</tr>
				<tr>
					<td width="13%">涉及系统：</td>
					<td width="20%">
						<select  name="relateToSystem" selWidth="177" selectedValue="${eventView.relateToSystem}" prompt="请选择涉及系统" 
						data='{"list":
						[{"value":"21","key":"21 空调系统"},{"value":"24","key":"24 电源"},{"value":"25","key":"25 设备/装饰"},{"value":"26","key":"26 防火"},
						{"value":"27","key":"27 飞行操纵"},{"value":"28","key":"28 燃油"},{"value":"29","key":"29 液压动力"},{"value":"30","key":"30 防冰和排雨"},
						{"value":"31","key":"31 仪表"},{"value":"32","key":"32 起落架"},{"value":"33","key":"33 灯光"},{"value":"34","key":"34 导航"},
						{"value":"35","key":"35 氧气"},{"value":"36","key":"36 气源"},{"value":"38","key":"38 水/污水"},{"value":"49","key":"49 机载辅助动力"},
						{"value":"51","key":"51 标准施工/结构"},{"value":"52","key":"52 舱门"},{"value":"53","key":"53 机身"},{"value":"54","key":"54 吊舱/吊架"},
						{"value":"55","key":"55 安定面"},{"value":"56","key":"56 窗"},{"value":"57","key":"57 机翼"},{"value":"61","key":"61 螺旋桨/推进器"},
						{"value":"63","key":"63 主旋翼传动"},{"value":"71","key":"71 动力装置"},{"value":"72","key":"72 涡轮/涡轮螺旋桨发动机"},{"value":"73","key":"73 发动机燃油和控制"},
						{"value":"74","key":"74 点火"},{"value":"75","key":"75 空气"},{"value":"76","key":"76 发动机控制"},{"value":"77","key":"77 发动机指示"},
						{"value":"78","key":"78 发动机排气"},{"value":"79","key":"79 发动机滑油"},{"value":"80","key":"80 起动"},{"value":"81","key":"81 涡轮增压"}]}'></select>
					</td>
					<td width="13%">飞机制造商:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="aircraftMake" value="${eventView.aircraftMake }"/>
					</td>
					<td width="13%">飞机型号:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="aircraftModel" value="${eventView.aircraftModel }"/>
					</td>
				</tr>
				<tr>
					<td width="13%">损伤程度:</td>
					<%-- <td width="20%">
						<input type="text" style="width:177px;" name="aircraftDamage" value="${eventView.aircraftDamage }"/>
					</td> --%>
					<td width="20%">
						<select  name="aircraftDamage" selWidth="177" selectedValue="${eventView.aircraftDamage}" prompt="请选择飞机损害程度" 
						data='{"list":[{"value":"destroy","key":"destroy"},{"value":"minor","key":"minor"},{"value":"none","key":"none"},{"value":"substantial","key":"substantial"},{"value":"unknown","key":"unknown"}]}'></select>
					</td>
					<td width="13%">发动机型号:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="engineModel" value="${eventView.engineModel }"/>
					</td>
					<td width="13%">发动机数量:</td>
					<%-- <td width="20%">
						<input type="text" style="width:177px;" name="numberOfEngines" value="${eventView.numberOfEngines }"/>
					</td> --%>
					<td width="20%">
						<select  name="numberOfEngines" selWidth="177" selectedValue="${eventView.numberOfEngines}" prompt="请选择发动机数量" 
						data='{"list":[{"value":"2","key":"2"},{"value":"3","key":"3"},{"value":"4","key":"4"},{"value":"unknown","key":"unknown"}]}'></select>
					</td>
				</tr>
				<tr>
					<td width="13%">运营商:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="operator" value="${eventView.operator }"/>
					</td>
					<td width="13%">污染物来源:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="pullutantSource" value="${eventView.pullutantSource }"/>
					</td>
					<td width="13%">备注:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="remark" value="${eventView.remark }"/>
					</td>
				</tr>
				<tr>
					<td width="13%">年龄:</td>
					<td width="20%" colspan="8">
						<input type="text" style="width:177px;" name="age" value=""/>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<button id="searchEvent" type="button"><span class="icon_find">查询</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button id="exportEvent" type="button"><span class="icon_export">导出</span></button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button"><span class="icon_reload">重置</span></button>
					</td>
				</tr>
			</table>
		</div>	
	</form>
	
		<div class="box2" panelTitle="AIDS数据源管理列表">
			<div style="height:35px;">
				<div class="float_left padding5">
					<button type="button" id="eventEditButton"><span class="icon_edit">事故编辑</span></button>&nbsp;
					<button type="button" id="eventDeleteButton"><span class="icon_delete">事故删除</span></button>&nbsp;
				</div>
			</div>
			<table class="tableStyle" mode="list" id="eventList" useCheckbox="true" selectRowButtonOnly="false">
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
							<input type="checkbox" name="id" value="${event.id }" />
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
						<td align="center">${event.weatherConditions }
							<c:if test="${not empty event.weather }">
								:${event.weather }
							</c:if>
						</td>
						<td align="center">${event.aircraftDamage }
							<c:if test="${not empty event.damage }">
								:${event.damage }
							</c:if>
						</td>
						<td align="center">${event.phaseFlight }
							<c:if test="${not empty event.phaseDesc }">
								:${event.phaseDesc }
							</c:if>
						</td> 
						<td align="center">
							<a onclick="detailEvents('${event.id }')">详细信息</a>
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