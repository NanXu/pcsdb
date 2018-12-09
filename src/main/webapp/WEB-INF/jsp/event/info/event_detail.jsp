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
	$("#eventButton").click(function() {
		var url = "${ctx}/event/info/listEvents";
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
					<td width="35%">${event.phaseFlight }
					<c:if test="${not empty event.phaseDesc }">:${event.phaseDesc }</c:if>
					</td>
				</tr>
				<tr>
					<td width="15%">飞行性质:</td>
					<td width="35%">${event.flightProperties }</td>
					<td width="15%">是否天气因素影响到人:</td>
					<td width="35%">${event.isWeatherFactor }</td>
				</tr>
				<tr>
					<td width="15%">是否影响到飞机系统:</td>
					<td width="35%">${event.isAffectAircraftSystem }</td>
					<td width="15%">是否存在人为因素:</td>
					<td width="35%">${event.isArtificialFactor }</td>
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
					<td width="13%">机组死亡:</td>
					<td width="20%">${casualties.crewFatal }</td>
					<td width="13%">机组重伤:</td>
					<td width="20%">${casualties.crewSerious }</td>
					<td width="13%">机组轻伤(未受伤):</td>
					<td width="20%">${casualties.crewMinor }</td>
				</tr>
				<tr>
					<td width="13%">乘客死亡:</td>
					<td width="20%">${casualties.passengerFatal }</td>
					<td width="13%">乘客重伤:</td>
					<td width="20%">${casualties.passengerSerious }</td>
					<td width="13%">乘客轻伤(未受伤):</td>
					<td width="20%">${casualties.passengerMinor }</td>
				</tr>
				<tr>
					<td width="13%">死亡:</td>
					<td width="20%">${casualties.fatalities }</td>
					<td width="13%">重伤:</td>
					<td width="20%">${casualties.injuries }</td>
				</tr>
			</table>
		</fieldset>
		<div class="height_15"></div>
		<fieldset>
			<legend>污染物信息</legend>
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">污染物来源:</td>
					<td width="35%">${pollutant.source }<c:if test="${not empty pollutant.sourceName }">:${pollutant.sourceName }</c:if></td>
					<td width="15%">涉及系统:</td>
					<td width="35%">${pollutant.relateToSystem }</td>
				</tr>
				<tr>
					<td width="15%">故障位置:</td>
					<td width="35%">${pollutant.bugLocation }</td>
					<td width="15%">故障模式:</td>
					<td width="35%">${pollutant.bugModel }<c:if test="${not empty pollutant.bugModelName }">:${pollutant.bugModelName }</c:if></td>
				</tr>
			</table>
		</fieldset>
		<div class="height_15"></div>
		<fieldset>
			<legend>采取措施</legend>
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">机组控制措施:</td>
					<td width="35%" colspan="3">${measures.prevent } ${measures.controls }</td>
					<%-- <td width="15%">其他机组控制措施:</td>
					<td width="35%">${measures.controls }</td> --%>
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
		<div class="height_5"></div>
		<div class="padding_top10">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td colspan="4">
						<input type="button" id="eventButton" value="返回"/>
					</td>
				</tr>
			</table>
		</div> 
	</div>
	<center></center>
</body>
</html>