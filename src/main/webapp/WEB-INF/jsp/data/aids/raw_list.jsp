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
	$("#importAidsRawData").click(function() {
		$.ajax({
			  type: 'GET',
			  url: '${ctx}/data/aids/importRaws',
			  dataType: 'JSON',
			  success: function(data){
					if(data.success){
						 window.parent.topWin.Dialog.alert("装载数据成功|提示",function(){
							 window.parent.changeIFrame('${ctx}/data/aids/listRaws');
						 });
					}else{
						 window.parent.topWin.Dialog.alert("装载数据失败|提示",function(){
						  });
					}
			  },
			  error:function(){
			  }			  
		});
	});
	
	$("#aidsRawDataIntoDb").click(function() {
		$.ajax({
			  type: 'POST',
			  url: '${ctx}/data/aids/extractRaws',
			  dataType: 'JSON',
			  success: function(data){
					if(data.success){
						 window.parent.topWin.Dialog.alert("数据入库成功|提示",function(){
							 window.parent.changeIFrame('${ctx}/data/aids/listRaws');
						 });
					}else{
						 window.parent.topWin.Dialog.alert("数据入库失败|提示",function(){
						  });
					}
			  },
			  error:function(){
			  }			  
		});
	});
	
	$("#aidsSpiderButton").click(function() {
		$.ajax({
			  type: 'POST',
			  url: '${ctx}/data/aids/spiderRaws',
			  dataType: 'JSON',
			  success: function(data){
					if(data.success){
						 window.parent.topWin.Dialog.alert(data.msg+"|提示",function(){
							 window.parent.changeIFrame('${ctx}/data/aids/listRaws');
						 });
					}else{
						 window.parent.topWin.Dialog.alert(data.msg+"|提示",function(){
						  });
					}
			  },
			  error:function(){
			  }			  
		});
	});
});

/**
 * 显示详细信息
 */
function detailRaws(reportNumber) {
	var url = "${ctx}/data/aids/detailRaws/"+reportNumber;
	window.parent.changeIFrame(url);
}
</script>
</head>
<body>
	<form action="${ctx}/data/aids/listRaws" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<div class="box2" panelTitle="查询条件">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">报告编号:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="reportNumber" value="${aidsRawData.reportNumber }"/>
					</td>
					<td width="13%">数据状态：</td>
					<td width="20%">
						<select  name="ready" selWidth="177" selectedValue="${aidsRawData.ready}" prompt="请选择数据状态" 
						data='{"list":[{"value":"2","key":"数据入库完成"},{"value":"1","key":"数据抓取完成"},{"value":"0","key":"csv文件读取完成"}]}'></select>
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
		</div>	
	</form>
	
		<div class="box2" panelTitle="AIDS数据源管理列表">
			<div style="height:35px;">
				<div class="float_left padding5">
					<%--<button type="button" id="importAidsRawData"><span class="icon_import">装载数据</span></button>&nbsp;&nbsp;--%>
					<button type="button" id="aidsRawDataIntoDb"><span class="icon_lock">数据入库</span></button>&nbsp;&nbsp;
					<%--<button type="button" id="aidsSpiderButton"><span class="icon_lock">数据抓取</span></button>--%>
				</div>
			</div>
			<table class="tableStyle" mode="list" id="userList" useCheckbox="true" selectRowButtonOnly="false">
				<tr>
					<th width="5%" align="center"></th>
					<th width="10%" align="center">报告编号</th>
					<th width="10%" align="center">当地日期</th>
					<th width="10%" align="center">城市</th>
					<th width="10%" align="center">州</th>
					<th width="10%" align="center">机场名称</th>
					<th width="10%" align="center">事故类型</th>
					<th width="10%" align="center">操作</th>
				</tr>
				<c:forEach var="aidsRawData" items="${page.list}" varStatus="status">
					<tr>
						<td class="ali02">
							<input type="checkbox" name="reportNumber" value="${aidsRawData.reportNumber }" />
						</td>
						<td align="center">${aidsRawData.reportNumber }</td>
						<td align="center">${aidsRawData.localEventDate }</td>
						<td align="center">${aidsRawData.eventCity }</td>
						<td align="center">${aidsRawData.eventState }</td>
						<td align="center">${aidsRawData.eventAirport }</td>
						<td align="center">${aidsRawData.eventType }</td>
						<td align="center">
							<a onclick="detailRaws('${aidsRawData.reportNumber}')">详细信息</a>
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