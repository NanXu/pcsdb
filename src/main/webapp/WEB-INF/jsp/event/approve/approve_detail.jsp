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
	$("#backApproveButton").click(function() {
		var url = "${ctx}/event/approve/listApproves";
		window.parent.changeIFrame(url);
		window.parent.topWin.Dialog.close();
	});
});
</script>
</head>
<body>
	<div style="width:100%;height:100%;" class="box1">
		<fieldset>
			<legend>审批信息</legend> 
			<table class="tableStyle" formMode="view" footer="normal">
				<tr>
					<td width="15%">详细信息:</td>
					<td width="85%" colspan="8">
						<textarea style="width:100%;" readonly="readonly">${remark }</textarea>
					</td>
				</tr>
		    </table>
		</fieldset>
		
		<div class="height_5"></div>
		<div class="padding_top10">
			<table class="tableStyle" formMode="transparent">
				<tr>
					<td colspan="4">
						<input type="button" id="backApproveButton" value="返回"/>
					</td>
				</tr>
			</table>
		</div> 
	</div>
	<center></center>
</body>
</html>