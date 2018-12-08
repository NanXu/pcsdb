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
	<form action="${ctx}/data/sdr/listRaws" id="searchForm" method="post">
		<input type="hidden" value="${pageNo}" name="pageNo" id="pageNo" />
		<input type="hidden" value="${pageSize}" name="pageSize" id="pageSize" />
		<div class="box2" panelTitle="查询条件">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td width="13%">日期序列号:</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="seqNumber" value="${sdrRawData.seqNumber }"/>
					</td>
					<td width="13%">ATA编码：</td>
					<td width="20%">
						<input type="text" style="width:177px;" name="ataCode" value="${sdrRawData.ataCode }"/>
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
	
		<div class="box2" panelTitle="SDR数据源管理列表">
			<!-- <div style="height:35px;">
				<div class="float_left padding5">
					<button type="button" id="importAidsRawData"><span class="icon_import">装载数据</span></button>&nbsp;&nbsp;
					<button type="button" id="aidsRawDataIntoDb"><span class="icon_lock">数据入库</span></button>&nbsp;&nbsp;
					<button type="button" id="aidsSpiderButton"><span class="icon_lock">数据抓取</span></button>
				</div>
			</div> -->
			<table class="tableStyle" mode="list" id="userList" useCheckbox="true" selectRowButtonOnly="false">
				<tr>
					<th width="5%" align="center"></th>
					<th width="10%" align="center">日期序列号</th>
					<th width="10%" align="center">发生日期</th>
					<th width="10%" align="center">ATA编码</th>
					<th width="10%" align="center">飞机制造商</th>
					<th width="10%" align="center">污染物来源</th>
					<th width="10%" align="center">飞行阶段</th>
					<!-- <th width="10%" align="center">操作</th> -->
				</tr>
				<c:forEach var="sdrRawData" items="${page.list}" varStatus="status">
					<tr>
						<td class="ali02">
							<input type="checkbox" name="reportNumber" value="${sdrRawData.id }" />
						</td>
						<td align="center">${sdrRawData.seqNumber }</td>
						<td align="center">${sdrRawData.occurrenceDate }</td>
						<td align="center">${sdrRawData.ataCode }</td>
						<td align="center">${sdrRawData.aircraftManufacturerName }</td>
						<td align="center">${sdrRawData.partName }</td>
						<td align="center">${sdrRawData.operationText }</td>
						<%-- <td align="center">
							<a onclick="detailRaws('${aidsRawData.reportNumber}')">详细信息</a>
						</td> --%>
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