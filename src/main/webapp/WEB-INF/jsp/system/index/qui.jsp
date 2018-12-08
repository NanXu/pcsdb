<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!--框架必需start-->
<link href="<%=basePath%>/js/qui/3.3/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>/js/qui/3.3/libs/skins/blue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="blue" positionTarget="positionContent"/>
<link href="<%=basePath%>/css/layout/skin/style.css" rel="stylesheet" type="text/css" id="skin" />
<script type="text/javascript" src="<%=basePath%>/js/qui/3.3/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/qui/3.3/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/qui/3.3/libs/js/main.js"></script>
<!--框架必需end-->

<!--引入弹窗组件start-->
<script type="text/javascript" src="<%=basePath%>/js/qui/3.3/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/qui/3.3/libs/js/popup/dialog.js"></script>
<!--引入弹窗组件end-->


