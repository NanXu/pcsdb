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
	$("#eventDraftButton").click(function() {
		var serialize = $("#editDraftEventForm").serialize();
		var url = '${ctx}/event/draft/editEvent';
		$.post(url, serialize, function(data) {
			if(data.success) {
				window.parent.topWin.Dialog.alert(data.msg+"|提示",function(){
					window.parent.changeIFrame('${ctx}/event/draft/listEvents');
					window.parent.topWin.Dialog.close();
				});
			} else {
				window.parent.topWin.Dialog.alert(data.msg+"|提示",function(){
					window.parent.topWin.Dialog.close();
				});
			}
		}, "json");
	}); 
	
	$("#eventDraftBackButton").click(function() {
		var url = "${ctx}/event/draft/listEvents";
		window.parent.changeIFrame(url);
	});
});
</script>
</head>
<body>
	<div style="width:100%;height:100%;" class="box1">
	<form id="editDraftEventForm" class="stepForm" stepFormTitle="true">
		<input type="hidden" name="event.id" value="${eventView.id }" />
		<div id="step1-1" class="stepForm" stepFormTitle="基本信息">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="15%">数据来源:</td>
					<td width="35%">
						<select  name="event.source" selWidth="177" selectedValue="${eventView.source}" prompt="请选择数据来源" 
							data='{"list":[{"value":"AIDS","key":"AIDS"},{"value":"SDR","key":"SDR"},{"value":"NTSB","key":"NTSB"}]}'
							class="validate[required] float_left" >
						</select>
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
					<td width="15%">来源标识:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="event.sourceId" value="${eventView.sourceId }" />
					</td>
				</tr>
				<tr>
					<td width="15%">事故类型:</td>
					<td width="35%">
						<select  name="event.eventType" selWidth="177" selectedValue="${eventView.eventType}" prompt="请选择事故类型" 
								data='{"list":[{"value":"INCIDENT","key":"INCIDENT"},{"value":"ACCIDENT","key":"ACCIDENT"}]}'
								class="validate[required] float_left" >
						</select>
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="event.eventType" value="${eventView.eventType }" /> --%>
					</td>
					<td width="15%">当地日期:</td>
					<td width="35%">
						<input type="text" class="date" style="width:70%;" name="event.localDate" value="${eventView.localDate }"/>
					</td>
				</tr>
				<tr>
					<td width="15%">最后起飞地:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="event.lastDeparturePoint" value="${eventView.lastDeparturePoint }" />
					</td>
					<td width="15%">目的地:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="event.destinationLocal" value="${eventView.destinationLocal }" />
					</td>
				</tr>
				<tr>
					<td width="15%">事件发生地:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="event.eventLocation" value="${eventView.eventLocation }" />
					</td>
					<td width="15%">天气状况:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="event.weatherConditions" value="${eventView.weatherConditions }" />
					</td>
				</tr>
				<tr>
					<td width="15%">飞机损害程度:</td>
					<td width="35%">
						<select  name="event.aircraftDamage" selWidth="177" selectedValue="${eventView.aircraftDamage}" prompt="请选择飞机损害程度" 
							data='{"list":[{"value":"destroy","key":"destroy"},{"value":"minor","key":"minor"},{"value":"none","key":"none"},{"value":"substantial","key":"substantial"},{"value":"unknown","key":"unknown"}]}'
							class="validate[required] float_left">
						</select>
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="event.aircraftDamage" value="${eventView.aircraftDamage }" /> --%>
					</td>
					<td width="15%">飞行阶段:</td>
					<td width="35%">
						<select  name="phaseFlight" selWidth="177" selectedValue="${eventView.phaseFlight}" prompt="请选择飞行阶段" 
							data='{"list":[{"value":"taxi","key":"taxi"},{"value":"takeoff","key":"Take off"},{"value":"climb","key":"climb"},{"value":"cruise","key":"cruise"},{"value":"descent","key":"descent"},{"value":"Approach","key":"Approach"},{"value":"landing","key":"landing"},{"value":"aborted","key":"aborted"},{"value":"other","key":"other"}]}'
							class="validate[required] float_left">
						</select>
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="event.phaseFlight" value="${eventView.phaseFlight }" /> --%>
					</td>
				</tr>
				<tr>
					<td width="15%">飞行性质:</td>
					<td width="35%">
						<select  name="event.flightProperties" selWidth="177" selectedValue="${eventView.flightProperties}" prompt="请选择飞行性质"
								 data='{"list":[{"value":"121部","key":"121部"},{"value":"91部","key":"91部"}]}'>
						</select>
					</td>
					<td width="15%">是否天气因素影响到人:</td>
					<td width="35%">
						<select  name="event.isWeatherFactor" selWidth="177" selectedValue="${eventView.isWeatherFactor}" prompt="请选择是否天气因素影响到人"
								 data='{"list":[{"value":"Y","key":"Y"},{"value":"N","key":"N"}]}'>
						</select>
					</td>
				</tr>
				<tr>
					<td width="15%">是否影响到飞机系统:</td>
					<td width="35%">
						<select  name="event.isAffectAircraftSystem" selWidth="177" selectedValue="${eventView.isAffectAircraftSystem}" prompt="请选择是否影响到飞机系统"
								 data='{"list":[{"value":"Y","key":"Y"},{"value":"N","key":"N"}]}'>
						</select>
					</td>
					<td width="15%">是否存在人为因素:</td>
					<td width="35%">
						<select  name="event.isArtificialFactor" selWidth="177" selectedValue="${eventView.isArtificialFactor}" prompt="请选择是否存在人为因素"
								 data='{"list":[{"value":"Y","key":"Y"},{"value":"N","key":"N"}]}'>
						</select>
					</td>
				</tr>
				<tr>
					<td width="15%">事件描述:</td>
					<td width="85%" colspan="3">
						<textarea style="width:100%;" name="event.eventRemarks">${eventView.eventRemarks }</textarea>
					</td>
				</tr>
				<tr>
					<td width="15%">原因描述:</td>
					<td width="85%" colspan="3">
						<textarea style="width:100%;" name="event.reasonRemarks">${eventView.reasonRemarks }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" value="下一步" selfTarget="step1-1" nextTarget="step1-2"/>
					</td>
				</tr>
		    </table>
		</div>
		
		<div id="step1-2" class="stepForm" stepFormTitle="飞机信息">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="15%">飞机型号:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="plane.aircraftModel" value="${eventView.aircraftModel }" class="validate[custom[noSpecialCaracters]]" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="plane.aircraftModel" value="${eventView.aircraftModel }" /> --%>
					</td>
					<td width="15%">飞机制造商:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="plane.aircraftMake" value="${eventView.aircraftMake }" class="validate[custom[onlyLetter]]" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="plane.aircraftMake" value="${eventView.aircraftMake }" /> --%>
					</td>
				</tr>
				<tr>
					<td width="15%">注册号:</td>
					<td width="35%">
						<input type="text" class="validate[custom[noSpecialCaracters]]" style="width:70%;" name="plane.registrationNumber" value="${eventView.registrationNumber }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="plane.registrationNumber" value="${eventView.registrationNumber }" /> --%>
					</td>
					<td width="15%">发动机制造商:</td>
					<td width="35%">
						<input type="text" class="validate[custom[noSpecialCaracters]]" style="width:70%;" name="plane.engineManufactuer" value="${eventView.engineManufactuer }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="plane.engineManufactuer" value="${eventView.engineManufactuer }" /> --%>
					</td>
				</tr>
				<tr>
					<td width="15%">发动机型号:</td>
					<td width="35%">
						<input type="text" class="validate[custom[noSpecialCaracters]]" style="width:70%;" name="plane.engineModel" value="${eventView.engineModel }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="plane.engineModel" value="${eventView.engineModel }" /> --%>
					</td>
					<td width="15%">发动机数量:</td>
					<td width="35%">
						<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="plane.numberOfEngines" value="${eventView.numberOfEngines }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="plane.numberOfEngines" value="${eventView.numberOfEngines }" /> --%>
					</td>
				</tr>
				<tr>
					<td width="15%">运营商:</td>
					<td width="35%">
						<input type="text" class="validate[custom[onlyLetter]]" style="width:70%;" name="plane.operator" value="${eventView.operator }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="plane.operator" value="${eventView.operator }" /> --%>
					</td>
					<td width="15%">运行规章:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="plane.runRules" value="${eventView.runRules }" />
					</td>
				</tr>
				<tr>
					<td width="15%">总飞行小时数:</td>
					<td width="35%">
						<input type="text" class="validate[custom[onlyNumberWide]]" style="width:70%;" name="plane.runTotalHours" value="${eventView.runTotalHours }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="plane.runTotalHours" value="${eventView.runTotalHours }" /> --%>
					</td>
					<td width="15%">维修类型:</td>
					<td width="35%">
						<select  name="plane.maintainType" selWidth="177" selectedValue="${eventView.maintainType}" prompt="请选择维修类型" 
							data='{"list":[{"value":"100H","key":"100H"},{"value":"AAIP","key":"AAIP"},{"value":"ANNL","key":"ANNL"},{"value":"COAW","key":"COAW"},{"value":"COND","key":"COND"},{"value":"UNK","key":"UNK"}]}'
							class="validate[required] float_left">
						</select>
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="plane.maintainType" value="${eventView.maintainType }" /> --%>
					</td>
				</tr>
				<tr>
					<td width="15%">维修后运行时间:</td>
					<td width="35%">
						<input type="text" class="validate[custom[onlyNumberWide]]" style="width:70%;" name="plane.maintainHours" value="${eventView.maintainHours }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="plane.maintainHours" value="${eventView.maintainHours }" /> --%>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" value="上一步" selfTarget="step1-2" prevTarget="step1-1"/>&nbsp;&nbsp;
						<input type="button" value="下一步" selfTarget="step1-2" nextTarget="step1-3"/>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="step1-3" class="stepForm" stepFormTitle="伤亡情况">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">机组死亡:</td>
					<td width="20%">
						<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="casualties.crewFatal" value="${eventView.crewFatal }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
					<td width="13%">机组重伤:</td>
					<td width="20%">
						<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="casualties.crewSerious" value="${eventView.crewSerious }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
					<td width="13%">机组轻伤(未受伤):</td>
					<td width="20%">
						<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="casualties.crewMinor" value="${eventView.crewMinor }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<tr>
					<td width="13%">乘客死亡:</td>
					<td width="20%">
						<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="casualties.passengerFatal" value="${eventView.passengerFatal }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
					<td width="13%">乘客重伤:</td>
					<td width="20%">
						<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="casualties.passengerFatal" value="${eventView.passengerSerious }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
					<td width="13%">乘客轻伤(未受伤):</td>
					<td width="20%">
						<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="casualties.passengerMinor" value="${eventView.passengerMinor }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<tr>
					<td width="13%">死亡:</td>
					<td width="20%">
						<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="casualties.fatalities" value="${eventView.fatalities }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="casualties.fatalities" value="${eventView.fatalities }" /> --%>
					</td>
					<td width="13%">重伤:</td>
					<td width="20%">
						<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="casualties.injuries" value="${eventView.injuries }" />
						<span class="star float_left">*</span>
						<div class="validation_info"></div><div class="clear"></div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" value="上一步" selfTarget="step1-3" prevTarget="step1-2"/>&nbsp;&nbsp;
						<input type="button" value="下一步" selfTarget="step1-3" nextTarget="step1-4"/>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="step1-4" class="stepForm" stepFormTitle="污染物信息">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="15%">污染物来源:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="pollutant.source" value="${eventView.pullutantSource }" />
					</td>
					<td width="15%">涉及系统:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="pollutant.relateToSystem" value="${eventView.relateToSystem }" />
					</td>
				</tr>
				<tr>
					<td width="15%">故障位置:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="pollutant.bugLocation" value="${eventView.bugLocation }" />
					</td>
					<td width="15%">故障模式:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="pollutant.bugModel" value="${eventView.bugModel }" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" value="上一步" selfTarget="step1-4" prevTarget="step1-3"/>&nbsp;&nbsp;
						<input type="button" value="下一步" selfTarget="step1-4" nextTarget="step1-5"/>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="step1-5" class="stepForm" stepFormTitle="采取措施">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="15%">预防/紧急措施:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="measures.prevent" value="${eventView.prevent }" />
					</td>
					<td width="15%">其他机组控制措施:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="measures.controls" value="${eventView.controls }" />
					</td>
				</tr>
				<tr>
					<td width="15%">维修措施:</td>
					<td width="35%">
						<input type="text" style="width:70%;" name="measures.maintain" value="${eventView.maintain }" />
					</td>
				</tr>
				<tr>
					<td width="15%">备注:</td>
					<td width="85%" colspan="3">
						<textarea style="width:100%;" name="measures.remark"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" value="上一步" selfTarget="step1-5" prevTarget="step1-4"/>&nbsp;&nbsp;
						<input type="button" id="eventDraftButton" value="提交"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
	</div>
	<center></center>
</body>
</html>