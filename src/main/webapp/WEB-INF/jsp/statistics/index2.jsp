<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../include/taglib.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/head.jsp"%>
<script type="text/javascript">
var testData={"rows":[
       		{"type":"事故类型","number":"10","name":"1212"},
       		{"type":"飞机损害程度","number":"12","name":"111"},
       		{"type":"飞行阶段","number":"10","name":"1212"},
       		{"type":"事故类型","number":"10","name":"1212"},
       		{"type":"飞行阶段","number":"10","name":"1212"},
       		{"type":"事故类型","number":"10","name":"1212"},
       		{"type":"事故类型","number":"10","name":"1212"},
       		{"type":"飞行阶段","number":"10","name":"1212"},
       		{"type":"飞机损害程度","number":"10","name":"1212"},
       		{"type":"事故类型","number":"10","name":"1212"},
       		{"type":"飞机损害程度","number":"10","name":"1212"},
       		{"type":"事故类型","number":"10","name":"1212"}]};
//数据表格使用
var g;
function initComplete(){
	 g = $("#maingrid").quiGrid({
              columns: [ 
              	{ display: '类别名称', name: 'name', align: 'center',  width: "30%"},
              	{ display: '类别', name: 'type', align: 'center',  width: "30%"},
              	{ display: '数量', name: 'number', align: 'center',  width: "40%",totalSummary: {render:function(obj){return '总数：'+obj.sum}}}
              ], 
              data:testData, usePager: false, sortName: 'id',rownumbers:true,height: '100%', width:"100%",groupColumnName: "type", groupColumnDisplay: "类别"
          });
}
</script>
</head>
<body>
<div id="maingrid" style="margin: 0; padding: 0"></div>
</body>
</html>