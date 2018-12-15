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
	/* $("#eventButton").click(function() {
		var url = "${ctx}/event/info/listEvents";
		window.parent.changeIFrame(url);
	});*/
	
	$("#saveEventButton").click(function() {
		var serialize = $("#eventAddForm").serialize();
		var url = '${ctx}/event/draft/addEvent';
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
	
	
});
</script>
</head>
<body>
	<div style="width:100%;height:100%;" class="box1">
		
		<form id="eventAddForm" class="stepForm" stepFormTitle="true">
			<div id="step1-1" class="stepForm" stepFormTitle="基本信息">
				<table class="tableStyle" formMode="transparent">
					<tr>
						<td width="15%">数据来源:</td>
						<td width="35%">
							<select  name="event.source" selWidth="177" selectedValue="${event.source}" prompt="请选择数据来源" 
								data='{"list":[{"value":"AIDS","key":"AIDS"},{"value":"SDR","key":"SDR"},{"value":"NTSB","key":"NTSB"}]}'
								class="validate[required] float_left" >
							</select>
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						</td>
						<td width="15%">来源标识:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="event.sourceId" value="${event.sourceId }" />
						</td>
					</tr>
					<tr>
						<td width="15%">事故类型:</td>
						<td width="35%">
							<select  name="event.eventType" selWidth="177" selectedValue="${event.eventType}" prompt="请选择事故类型" 
								data='{"list":[{"value":"INCIDENT","key":"INCIDENT"},{"value":"ACCIDENT","key":"ACCIDENT"}]}'
								class="validate[required] float_left" >
							</select>
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						<%-- <input type="text" style="width:70%;" name="event.eventType" value="${event.eventType }" /> --%>
						</td>
						<td width="15%">当地日期:</td>
						<td width="35%">
							<input type="text" class="date" style="width:70%;" name="event.localDate" value="${event.localDate }"/>
						</td>
					</tr>
					<tr>
						<td width="15%">最后起飞地:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="event.lastDeparturePoint" value="${event.lastDeparturePoint }" />
						</td>
						<td width="15%">目的地:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="event.destinationLocal" value="${event.destinationLocal }" />
						</td>
					</tr>
					<tr>
						<td width="15%">事件发生地:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="event.eventLocation" value="${event.eventLocation }" />
						</td>
						<td width="15%">天气状况:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="event.weatherConditions" value="${event.weatherConditions }" />
						</td>
					</tr>
					<tr>
						<td width="15%">飞机损害程度:</td>
						<td width="35%">
							<select  name="event.aircraftDamage" selWidth="177" selectedValue="${event.aircraftDamage}" prompt="请选择飞机损害程度" 
								data='{"list":[{"value":"destroy","key":"destroy"},{"value":"minor","key":"minor"},{"value":"none","key":"none"},{"value":"substantial","key":"substantial"},{"value":"unknown","key":"unknown"}]}'
								class="validate[required] float_left">
							</select>
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
							
							<%-- <input type="text" style="width:70%;" name="event.aircraftDamage" value="${event.aircraftDamage }" /> --%>
						</td>
						<td width="15%">飞行阶段:</td>
						<td width="35%">
							<select  name="phaseFlight" selWidth="177" selectedValue="${event.phaseFlight}" prompt="请选择飞行阶段"
								data='{"list":[{"value":"taxi","key":"taxi"},{"value":"takeoff","key":"Take off"},{"value":"climb","key":"climb"},{"value":"cruise","key":"cruise"},{"value":"descent","key":"descent"},{"value":"Approach","key":"Approach"},{"value":"landing","key":"landing"},{"value":"aborted","key":"aborted"},{"value":"other","key":"other"}]}'
								class="validate[required] float_left">
							</select>
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
							<%-- <input type="text" style="width:70%;" name="event.phaseFlight" value="${event.phaseFlight }" /> --%>
						</td>
					</tr>
					<tr>
						<td width="15%">飞行性质:</td>
						<td width="35%">
							<select  name="event.flightProperties" selWidth="177" selectedValue="${event.flightProperties}" prompt="请选择飞行性质"
									 data='{"list":[{"value":"121部","key":"121部"},{"value":"91部","key":"91部"}]}'>
							</select>
						</td>
						<td width="15%">是否天气因素影响到人:</td>
						<td width="35%">
							<select  name="event.isWeatherFactor" selWidth="177" selectedValue="${event.isWeatherFactor}" prompt="请选择是否天气因素影响到人"
									 data='{"list":[{"value":"Y","key":"Y"},{"value":"N","key":"N"}]}'>
							</select>
						</td>
					</tr>
					<tr>
						<td width="15%">是否影响到飞机系统:</td>
						<td width="35%">
							<select  name="event.isAffectAircraftSystem" selWidth="177" selectedValue="${event.isAffectAircraftSystem}" prompt="请选择是否影响到飞机系统"
									 data='{"list":[{"value":"Y","key":"Y"},{"value":"N","key":"N"}]}'>
							</select>
						</td>
						<td width="15%">是否存在人为因素:</td>
						<td width="35%">
							<select  name="event.isArtificialFactor" selWidth="177" selectedValue="${event.isArtificialFactor}" prompt="请选择是否存在人为因素"
									 data='{"list":[{"value":"Y","key":"Y"},{"value":"N","key":"N"}]}'>
							</select>
						</td>
					</tr>
					<tr>
						<td width="15%">事件描述:</td>
						<td width="85%" colspan="3">
							<textarea style="width:100%;" name="event.eventRemarks"></textarea>
						</td>
					</tr>
					<tr>
						<td width="15%">原因描述:</td>
						<td width="85%" colspan="3">
							<textarea style="width:100%;" name="event.reasonRemarks"></textarea>
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
							<input type="text" style="width:70%;" name="plane.aircraftModel" value="${plane.aircraftModel }" class="validate[custom[noSpecialCaracters]]" />
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						</td>
						<td width="15%">飞机制造商:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="plane.aircraftMake" value="${plane.aircraftMake }" class="validate[custom[onlyLetter]]" />
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						</td>
					</tr>
					<tr>
						<td width="15%">注册号:</td>
						<td width="35%">
							<input type="text" class="validate[custom[noSpecialCaracters]]" style="width:70%;" name="plane.registrationNumber" value="${plane.registrationNumber }" />
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						</td>
						<td width="15%">发动机制造商:</td>
						<td width="35%">
							<input type="text" class="validate[custom[noSpecialCaracters]]" style="width:70%;" name="plane.engineManufactuer" value="${plane.engineManufactuer }" />
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						</td>
					</tr>
					<tr>
						<td width="15%">发动机型号:</td>
						<td width="35%">
							<input type="text" class="validate[custom[noSpecialCaracters]]" style="width:70%;" name="plane.engineModel" value="${plane.engineModel }" />
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						</td>
						<td width="15%">发动机数量:</td>
						<td width="35%">
							<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="plane.numberOfEngines" value="${plane.numberOfEngines }" />
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						</td>
					</tr>
					<tr>
						<td width="15%">运营商:</td>
						<td width="35%">
							<input type="text" class="validate[custom[onlyLetter]]" style="width:70%;" name="plane.operator" value="${plane.operator }" />
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						</td>
						<td width="15%">运行规章:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="plane.runRules" value="${plane.runRules }" />
						</td>
					</tr>
					<tr>
						<td width="15%">总飞行小时数:</td>
						<td width="35%">
							<input type="text" class="validate[custom[onlyNumberWide]]" style="width:70%;" name="plane.runTotalHours" value="${plane.runTotalHours }" />
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						</td>
						<td width="15%">维修类型:</td>
						<td width="35%">
							<select  name="plane.maintainType" selWidth="177" selectedValue="${plane.maintainType}" prompt="请选择维修类型" 
								data='{"list":[{"value":"100H","key":"100H"},{"value":"AAIP","key":"AAIP"},{"value":"ANNL","key":"ANNL"},{"value":"COAW","key":"COAW"},{"value":"COND","key":"COND"},{"value":"UNK","key":"UNK"}]}'
								class="validate[required] float_left">
							</select>
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
							<%-- <input type="text" style="width:70%;" name="plane.maintainType" value="${plane.maintainType }" /> --%>
						</td>
					</tr>
					<tr>
						<td width="15%">维修后运行时间:</td>
						<td width="35%">
							<input type="text" class="validate[custom[onlyNumberWide]]" style="width:70%;" name="plane.maintainHours" value="${plane.maintainHours }" />
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
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
						<td width="15%">死亡:</td>
						<td width="35%">
							<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="casualties.fatalities" value="${casualties.fatalities }" />
							<span class="star float_left">*</span>
							<div class="validation_info"></div><div class="clear"></div>
						</td>
						<td width="15%">受伤:</td>
						<td width="35%">
							<input type="text" class="validate[custom[onlyNumber]]" style="width:70%;" name="casualties.injuries" value="${casualties.injuries }" />
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
							<input type="text" style="width:70%;" name="pollutant.source" value="${pollutant.source }" />
						</td>
						<td width="15%">涉及系统:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="pollutant.relateToSystem" value="${pollutant.relateToSystem }" />
						</td>
					</tr>
					<tr>
						<td width="15%">故障位置:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="pollutant.bugLocation" value="${pollutant.bugLocation }" />
						</td>
						<td width="15%">故障模式:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="pollutant.bugModel" value="${pollutant.bugModel }" />
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
							<input type="text" style="width:70%;" name="measures.prevent" value="${measures.prevent }" />
						</td>
						<td width="15%">其他机组控制措施:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="measures.controls" value="${measures.controls }" />
						</td>
					</tr>
					<tr>
						<td width="15%">维修措施:</td>
						<td width="35%">
							<input type="text" style="width:70%;" name="measures.maintain" value="${measures.maintain }" />
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
							<input type="button" id="saveEventButton" value="提交"/>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>