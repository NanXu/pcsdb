<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../include/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AIDS数据详细信息页面</title>
<%@ include file="../../include/head.jsp"%>
<script type="text/javascript">
$(function() {
	$("#aidsBackButton").click(function() {
		var url = "${ctx}/data/aids/listRaws";
		window.parent.changeIFrame(url);
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
					<td width="15%">报告编号:</td>
					<td width="35%">${aidsRawData.reportNumber }</td>
					<td width="15%">当地日期:</td>
					<td width="35%">${aidsRawData.localEventDate }</td>
				</tr>
				<tr>
					<td width="15%">城市:</td>
					<td width="35%">${aidsRawData.eventCity }</td>
					<td width="15%">州:</td>
					<td width="35%">${aidsRawData.eventState }</td>
				</tr>
				<tr>
					<td width="15%">机场名称:</td>
					<td width="35%">${aidsRawData.eventAirport }</td>
					<td width="15%">事故类型:</td>
					<td width="35%">${aidsRawData.eventType }</td>
				</tr>
		    </table>
		</fieldset>
		<div class="height_15"></div>
		<fieldset>
			<legend>飞机信息</legend>
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">飞机损伤:</td>
					<td width="35%">${aidsRawData.aircraftDamage }</td>
					<td width="15%">飞机制造商:</td>
					<td width="35%">${aidsRawData.aircraftMake }</td>
				</tr>
				<tr>
					<td width="15%">飞机型号:</td>
					<td width="35%">${aidsRawData.aircraftModel }</td>
					<td width="15%">飞机序列号:</td>
					<td width="35%">${aidsRawData.aircraftSeries }</td>
				</tr>
				<tr>
					<td width="15%">飞行总时长:</td>
					<td width="35%">${aidsRawData.picFlightTimeTotalHrs }</td>
					<td width="15%">运营商:</td>
					<td width="35%">${aidsRawData.operator }</td>
				</tr>
			</table>
		</fieldset>
		<div class="height_15"></div>
		<fieldset>
			<legend>细节信息</legend>
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">主要飞行类型:</td>
					<td width="35%">${aidsRawData.primaryFlightType }</td>
					<td width="15%">注册号:</td>
					<td width="35%">${aidsRawData.aircraftRegistrationNbr }</td>
				</tr>
				<tr>
					<td width="15%">死亡:</td>
					<td width="35%">${aidsRawData.totalFatalities }</td>
					<td width="15%">受伤:</td>
					<td width="35%">${aidsRawData.totalInjuries }</td>
				</tr>
				<tr>
					<td width="15%">发动机制造商:</td>
					<td width="35%">${aidsRawData.aircraftMake }</td>
					<td width="15%">发动机型号:</td>
					<td width="35%">${aidsRawData.aircraftModel }</td>
				</tr>
				<tr>
					<td width="15%">发动机群编号:</td>
					<td width="35%">${aidsRawData.engineGroupCode }</td>
					<td width="15%">发动机数量:</td>
					<td width="35%">${aidsRawData.nbrofEngines }</td>
				</tr>
			</table>
		</fieldset>
		<div class="height_15"></div>
		<fieldset>
			<legend>环境/运营信息</legend>
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">主要飞行环境:</td>
					<td width="35%">${aidsRawData.flightConductCode }</td>
					<td width="15%">飞行计划申请:</td>
					<td width="35%">${aidsRawData.flightPlanFiledCode }</td>
				</tr>
			</table>
		</fieldset>
		<div class="height_15"></div>
		<fieldset>
			<legend>事件简述</legend>
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">事件简述:</td>
					<td width="85%" colspan="3">
						<textarea style="width:100%;" readonly="readonly">${aidsRawData.eventRemarks }</textarea>
					</td>
				</tr>
			</table>
		</fieldset>
		<div class="height_5"></div>
		<div class="padding_top10">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td colspan="4">
						<input type="button" id="aidsBackButton" value="返回"/>
					</td>
				</tr>
			</table>
		</div> 
	</div>
	<center></center>
</body>
</html>