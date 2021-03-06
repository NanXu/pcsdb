<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../include/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>事故详细信息页面</title>
<%@ include file="../../include/head.jsp"%>
<script type="text/javascript">
$(function() {
	//
	$("#approvePassedButton").click(function() {
		$("#pass").val("1");
		var valid = validateForm("#approveEventForm");
		if(valid){
			var serialize = $("#approveEventForm").serialize();
			var url = '${ctx}/event/approve/approveEvent';
			$.post(url, serialize, function(data) {
				if(data.success) {
					window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
						window.parent.changeIFrame('${ctx}/event/approve/listEvents');
						window.parent.topWin.Dialog.close();
					});
				} else {
					window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
						window.parent.topWin.Dialog.close();
					});
				}
			}, "json");
		}
	});
	
	$("#approveUnPassButton").click(function() {
		$("#pass").val("0");
		var valid = validateForm("#approveEventForm");
		if(valid){
			var serialize = $("#approveEventForm").serialize();
			var url = '${ctx}/event/approve/approveEvent';
			$.post(url, serialize, function(data) {
				if(data.success) {
					window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
						window.parent.changeIFrame('${ctx}/event/approve/listEvents');
						window.parent.topWin.Dialog.close();
					});
				} else {
					window.parent.topWin.Dialog.alert(data.msg + "|提示",function(){
						window.parent.topWin.Dialog.close();
					});
				}
			}, "json");
		}
	});
});
</script>
</head>
<body>
	<div style="width:100%;height:100%;" class="box1">
		<fieldset>
			<legend>基本信息</legend> 
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">数据来源:</td>
					<td width="35%">${event.source }</td>
					<td width="15%">来源标识:</td>
					<td width="35%">${event.sourceId }</td>
				</tr>
				<tr>
					<td width="15%">事故类型:</td>
					<td width="35%">${event.eventType }</td>
					<td width="15%">当地日期:</td>
					<td width="35%">
						<fmt:formatDate value="${event.localDate }" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td width="15%">最后起飞地:</td>
					<td width="35%">${event.lastDeparturePoint }</td>
					<td width="15%">目的地:</td>
					<td width="35%">${event.destinationLocal }</td>
				</tr>
				<tr>
					<td width="15%">事件发生地:</td>
					<td width="35%">${event.eventLocation }</td>
					<td width="15%">天气状况:</td>
					<td width="35%">${event.weatherConditions }</td>
				</tr>
				<tr>
					<td width="15%">飞机损害程度:</td>
					<td width="35%">${event.aircraftDamage }</td>
					<td width="15%">飞行阶段:</td>
					<td width="35%">${event.phaseFlight }</td>
				</tr>
				<tr>
					<td width="15%">事件描述:</td>
					<td width="85%" colspan="3">
						<textarea style="width:100%;" readonly="readonly">${event.eventRemarks }</textarea>
					</td>
				</tr>
				<tr>
					<td width="15%">原因描述:</td>
					<td width="85%" colspan="3">
						<textarea style="width:100%;" readonly="readonly">${event.reasonRemarks }</textarea>
					</td>
				</tr>
		    </table>
		</fieldset>
		<div class="height_15"></div>
		<fieldset>
			<legend>飞机信息</legend>
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">飞机型号:</td>
					<td width="35%">${plane.aircraftModel }</td>
					<td width="15%">飞机制造商:</td>
					<td width="35%">${plane.aircraftMake }</td>
				</tr>
				<tr>
					<td width="15%">注册号:</td>
					<td width="35%">${plane.registrationNumber }</td>
					<td width="15%">发动机制造商:</td>
					<td width="35%">${plane.engineManufactuer }</td>
				</tr>
				<tr>
					<td width="15%">发动机型号:</td>
					<td width="35%">${plane.engineModel }</td>
					<td width="15%">发动机数量:</td>
					<td width="35%">${plane.numberOfEngines }</td>
				</tr>
				<tr>
					<td width="15%">运营商:</td>
					<td width="35%">${plane.operator }</td>
					<td width="15%">运行规章:</td>
					<td width="35%">${plane.runRules }</td>
				</tr>
				<tr>
					<td width="15%">总飞行小时数:</td>
					<td width="35%">${plane.runTotalHours }</td>
					<td width="15%">维修类型:</td>
					<td width="35%">${plane.maintainType }</td>
				</tr>
				<tr>
					<td width="15%">维修后运行时间:</td>
					<td width="35%">${plane.maintainHours }</td>
				</tr>
			</table>
		</fieldset>
		<div class="height_15"></div>
		<fieldset>
			<legend>伤亡情况</legend>
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">死亡:</td>
					<td width="35%">${casualties.fatalities }</td>
					<td width="15%">受伤:</td>
					<td width="35%">${casualties.injuries }</td>
				</tr>
			</table>
		</fieldset>
		<div class="height_15"></div>
		<fieldset>
			<legend>污染物信息</legend>
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">污染物来源:</td>
					<td width="35%">${pollutant.source }</td>
					<td width="15%">涉及系统:</td>
					<td width="35%">${pollutant.relateToSystem }</td>
				</tr>
				<tr>
					<td width="15%">故障位置:</td>
					<td width="35%">${pollutant.bugLocation }</td>
					<td width="15%">故障模式:</td>
					<td width="35%">${pollutant.bugModel }</td>
				</tr>
			</table>
		</fieldset>
		<div class="height_15"></div>
		<fieldset>
			<legend>采取措施</legend>
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">预防/紧急措施:</td>
					<td width="35%">${measures.prevent }</td>
					<td width="15%">其他机组控制措施:</td>
					<td width="35%">${measures.controls }</td>
				</tr>
				<tr>
					<td width="15%">维修措施:</td>
					<td width="35%">${measures.maintain }</td>
				</tr>
				<tr>
					<td width="15%">备注:</td>
					<td width="85%" colspan="3">
						<textarea style="width:100%;" readonly="readonly">${measures.remark }</textarea>
					</td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset>
			<legend>审批信息</legend>
			<form id="approveEventForm" method="post" target="frmright" showOnMouseOver="false">
				<input type="hidden" id="eventId" name="eventId" value="${event.id }">
				<input type="hidden" id="pass" name="pass" value="${approveInfo.pass }">
				<table class="tableStyle" formMode="line">
					<tr>
						<td width="15%">审批意见:</td>
						<td width="85%" colspan="3">
							<textarea style="width:100%;" name="remark"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</fieldset>
		<div class="height_5"></div>
		<div class="padding_top10">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td colspan="4">
						<input type="button" id="approvePassedButton" value="通过"/>&nbsp;&nbsp;
						<input type="button" id="approveUnPassButton" value="不通过"/>
					</td>
				</tr>
			</table>
		</div> 
	</div>
	<center></center>
</body>
</html>